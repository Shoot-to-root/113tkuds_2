
import java.util.Scanner;

public class M01_BuildHeap {

    /*
   * Time Complexity: O(n)
   * 說明：雖然單次 heapifyDown 是 O(log n)，但建堆時，大部分的節點位於樹的底部，其 heapifyDown 只需要常數次操作。
   * 總體而言，所有節點的 heapifyDown 操作次數總和可以證明是 O(n)。
     */
    public static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, type);
        }
    }

    private static void heapifyDown(int[] arr, int n, int i, String type) {
        int target = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if ("max".equals(type)) {
            if (left < n && arr[left] > arr[target]) {
                target = left;
            }
            if (right < n && arr[right] > arr[target]) {
                target = right;
            }
        } else { // "min"
            if (left < n && arr[left] < arr[target]) {
                target = left;
            }
            if (right < n && arr[right] < arr[target]) {
                target = right;
            }
        }

        if (target != i) {
            swap(arr, i, target);
            heapifyDown(arr, n, target, type);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = scanner.nextLine();

        int n = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        buildHeap(arr, type);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        scanner.close();
    }
}
