
import java.util.Arrays;

public class SelectionSortImplementation {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列為空或只有一個元素，無需排序。");
            return;
        }

        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;
        long startTime = System.currentTimeMillis();

        System.out.println("====== 選擇排序過程 ======");
        System.out.println("原始陣列: " + Arrays.toString(arr));

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++; // 計算比較次數
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++; // 計算交換次數
            }

            System.out.printf("第 %d 輪排序後: %s\n", i + 1, Arrays.toString(arr));
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("--------------------------");
        System.out.println("最終排序結果: " + Arrays.toString(arr));
        System.out.printf("總比較次數: %d\n", comparisons);
        System.out.printf("總交換次數: %d\n", swaps);
        System.out.printf("排序時間: %d 毫秒\n", duration); // 比較與氣泡排序的效能差異
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列為空或只有一個元素，無需排序。");
            return;
        }

        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;
        long startTime = System.currentTimeMillis();

        System.out.println("\n====== 氣泡排序過程 ======");
        System.out.println("原始陣列: " + Arrays.toString(arr));

        for (int i = 0; i < n - 1; i++) {
            boolean swappedInPass = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swappedInPass = true;
                }
            }
            System.out.printf("第 %d 輪排序後: %s\n", i + 1, Arrays.toString(arr));

            if (!swappedInPass) {
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("--------------------------");
        System.out.println("最終排序結果: " + Arrays.toString(arr));
        System.out.printf("總比較次數: %d\n", comparisons);
        System.out.printf("總交換次數: %d\n", swaps);
        System.out.printf("排序時間: %d 毫秒\n", duration); // 比較與氣泡排序的效能差異
    }

    public static void main(String[] args) {
        int[] dataForSelectionSort = {64, 25, 12, 22, 11};
        int[] dataForBubbleSort = Arrays.copyOf(dataForSelectionSort, dataForSelectionSort.length);

        selectionSort(dataForSelectionSort);

        System.out.println("\n--------------------------\n");

        bubbleSort(dataForBubbleSort);

        System.out.println("\n\n====== 總結效能差異 ======");
        System.out.println("選擇排序 (Selection Sort):");
        System.out.println("- 特點: 交換次數較少，在寫入操作成本高時更具優勢。");
        System.out.println("- 總交換次數: 5個元素的陣列最多為 4 次。");
        System.out.println("\n氣泡排序 (Bubble Sort):");
        System.out.println("- 特點: 交換次數較多，但對於幾乎已排序的陣列，可透過提前終止來提升效率。");
        System.out.println("- 總交換次數: 5個元素的陣列最多可達 O(n^2) 次。");
        System.out.println("\n時間複雜度皆為 O(n^2)。實際執行時間會受到硬體和陣列大小的影響。");
    }
}
