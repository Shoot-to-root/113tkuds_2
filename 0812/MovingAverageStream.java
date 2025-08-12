import java.util.*;

public class MovingAverageStream {
    private final int size;
    private final Queue<Integer> window;
    private double sum;

    private PriorityQueue<Integer> low;  // max heap
    private PriorityQueue<Integer> high; // min heap

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private Map<Integer, Integer> delayed;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0.0;

        low = new PriorityQueue<>(Collections.reverseOrder());
        high = new PriorityQueue<>();

        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        delayed = new HashMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;

        // 插入中位數結構
        if (low.isEmpty() || val <= low.peek()) {
            low.offer(val);
        } else {
            high.offer(val);
        }
        balanceHeaps();

        // 插入最小 & 最大結構
        minHeap.offer(val);
        maxHeap.offer(val);

        // 超過視窗大小，移除最舊元素
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;

            // 標記延遲刪除
            delayed.put(removed, delayed.getOrDefault(removed, 0) + 1);

            // 從中位數結構移除
            if (removed <= low.peek()) {
                prune(low);
            } else {
                prune(high);
            }
            balanceHeaps();

            // 從最小 & 最大結構延遲刪除
            prune(minHeap);
            prune(maxHeap);
        }

        return sum / window.size();
    }

    public double getMedian() {
        if (low.size() > high.size()) {
            return low.peek();
        } else if (low.size() < high.size()) {
            return high.peek();
        } else {
            return (low.peek() + high.peek()) / 2.0;
        }
    }

    public int getMin() {
        prune(minHeap);
        return minHeap.peek();
    }

    public int getMax() {
        prune(maxHeap);
        return maxHeap.peek();
    }

    private void balanceHeaps() {
        if (low.size() > high.size() + 1) {
            high.offer(low.poll());
            prune(low);
        } else if (high.size() > low.size()) {
            low.offer(high.poll());
            prune(high);
        }
    }

    // 延遲刪除
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.peek();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
            heap.poll();
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println("ma.next(1) = " + ma.next(1));
        System.out.println("ma.next(10) = " + ma.next(10));
        System.out.println("ma.next(3) = " + String.format("%.2f", ma.next(3)));
        System.out.println("ma.next(5) = " + ma.next(5));
        System.out.println("ma.getMedian() = " + ma.getMedian());
        System.out.println("ma.getMin() = " + ma.getMin());
        System.out.println("ma.getMax() = " + ma.getMax());
    }
}
