import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class M03_TopKConvenience {

    static class Item {
        String name;
        int qty;

        public Item(String name, int qty) {
            this.name = name;
            this.qty = qty;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        /*
         * Time Complexity: O(n log K)
         * 說明：建構一個大小為 K 的最小堆。對 n 個商品，每個商品的操作為 O(log K)。
         * 總時間為 n * O(log K) = O(n log K)。最後排序操作為 O(K log K)，但因為 K 遠小於 n，此項可忽略。
         */

        // Comparator 確保堆頂元素總是銷量最小的。
        // 為了處理銷量相同的情況，此處沒有額外處理，即保持不穩定排序。
        PriorityQueue<Item> minHeap = new PriorityQueue<>(Comparator.comparingInt(item -> item.qty));

        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int qty = scanner.nextInt();
            Item item = new Item(name, qty);

            minHeap.offer(item); 

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        scanner.close();

        Item[] topKItems = minHeap.toArray(new Item[0]);

        java.util.Arrays.sort(topKItems, (a, b) -> Integer.compare(b.qty, a.qty));

        for (Item item : topKItems) {
            System.out.println(item.name + " " + item.qty);
        }
    }
}