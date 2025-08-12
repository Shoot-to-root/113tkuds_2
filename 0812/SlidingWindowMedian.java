import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 存小的一半
    private PriorityQueue<Integer> minHeap; // 存大的一半
    private Map<Integer, Integer> delayed;  // 用 delayed map 做延遲刪除，確保滑動視窗移除元素時不破壞 Heap 性質

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];
        maxHeap.clear();
        minHeap.clear();
        delayed.clear();

        for (int i = 0; i < n; i++) {
            addNum(nums[i]);

            if (i >= k) {
                removeNum(nums[i - k]);
            }

            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
            }
        }

        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        if (num <= maxHeap.peek()) {
            // 這個數在 maxHeap
            prune(maxHeap);
        } else {
            prune(minHeap);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        // 保持大小差不超過 1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            prune(maxHeap);
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) delayed.remove(num);
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[][] testArrays = {
            {1, 3, -1, -3, 5, 3, 6, 7},
            {1, 2, 3, 4}
        };
        int[] ks = {3, 2};

        for (int t = 0; t < testArrays.length; t++) {
            int[] nums = testArrays[t];
            int k = ks[t];

            System.out.print("陣列：[");
            for (int i = 0; i < nums.length; i++) {
                System.out.print(nums[i]);
                if (i < nums.length - 1) System.out.print(", ");
            }
            System.out.println("], K=" + k);

            double[] res = swm.medianSlidingWindow(nums, k);
            System.out.print("輸出：[");
            for (int i = 0; i < res.length; i++) {
                if (res[i] == (long) res[i]) {
                    System.out.print((long) res[i]);
                } else {
                    System.out.print(res[i]);
                }
                if (i < res.length - 1) System.out.print(", ");
            }
            System.out.println("]\n");
        }
    }
}
