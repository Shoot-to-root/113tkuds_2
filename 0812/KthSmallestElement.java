import java.util.PriorityQueue;
import java.util.Collections;

public class KthSmallestElement {

    // 方法1：使用大小為 K 的 Max Heap
    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    // 方法2：使用 Min Heap 然後提取 K 次
    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int t = 0; t < testArrays.length; t++) {
            int[] arr = testArrays[t];
            int k = ks[t];

            System.out.print("陣列：[");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
                if (i < arr.length - 1) System.out.print(", ");
            }
            System.out.println("], K=" + k);

            long start1 = System.nanoTime();
            int ans1 = kthSmallestMaxHeap(arr, k);
            long time1 = System.nanoTime() - start1;

            long start2 = System.nanoTime();
            int ans2 = kthSmallestMinHeap(arr, k);
            long time2 = System.nanoTime() - start2;

            System.out.println("答案（方法1 Max Heap）： " + ans1 + "，耗時：" + time1 + " ns");
            System.out.println("答案（方法2 Min Heap）： " + ans2 + "，耗時：" + time2 + " ns");
            System.out.println();
        }
    }
}
