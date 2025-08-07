import java.util.Arrays;

public class AdvancedArrayRecursion {

    public static void main(String[] args) {

        System.out.println("--- 遞迴實作快速排序演算法 ---");
        int[] arrForSort = {10, 7, 8, 9, 1, 5};
        System.out.println("原始陣列: " + Arrays.toString(arrForSort));
        quickSort(arrForSort);
        System.out.println("快速排序後: " + Arrays.toString(arrForSort));
        System.out.println();

        System.out.println("--- 遞迴合併兩個已排序的陣列 ---");
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8, 9};
        System.out.println("陣列1: " + Arrays.toString(arr1));
        System.out.println("陣列2: " + Arrays.toString(arr2));
        int[] mergedArray = merge(arr1, arr2);
        System.out.println("遞迴合併後: " + Arrays.toString(mergedArray));
        System.out.println();

        System.out.println("--- 遞迴尋找陣列中的第 k 小元素 ---");
        int[] arrForKth = {10, 7, 8, 9, 1, 5, 3};
        int k = 3; // 假設要找第3小的元素 
        System.out.println("陣列: " + Arrays.toString(arrForKth));
        int kthElement = findKthSmallest(arrForKth, k);
        System.out.println("第 " + k + " 小的元素是: " + kthElement);
        System.out.println();
        
        System.out.println("--- 遞迴檢查陣列是否存在子序列總和等於目標值 ---");
        int[] arrForSum = {3, 34, 4, 12, 5, 2};
        int target1 = 9; // 存在 
        int target2 = 30; // 不存在
        System.out.println("陣列: " + Arrays.toString(arrForSum));
        System.out.println("是否存在總和為 " + target1 + " 的子序列? " + subsetSumExists(arrForSum, target1));
        System.out.println("是否存在總和為 " + target2 + " 的子序列? " + subsetSumExists(arrForSum, target2));
    }

    // 1. 遞迴實作快速排序
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    private static void quickSortRecursive(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortRecursive(arr, low, pivotIndex - 1);
            quickSortRecursive(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // 交換 arr[i] 和 arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 交換 arr[i+1] 和 arr[high] (基準點)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // 2. 遞迴合併兩個已排序的陣列
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        mergeRecursive(arr1, arr2, merged, 0, 0, 0);
        return merged;
    }

    private static void mergeRecursive(int[] arr1, int[] arr2, int[] merged, int i, int j, int k) {
        // 基礎情況：其中一個陣列已完全複製
        if (i >= arr1.length) {
            System.arraycopy(arr2, j, merged, k, arr2.length - j);
            return;
        }
        if (j >= arr2.length) {
            System.arraycopy(arr1, i, merged, k, arr1.length - i);
            return;
        }

        // 遞迴步驟：比較並複製較小的元素
        if (arr1[i] <= arr2[j]) {
            merged[k] = arr1[i];
            mergeRecursive(arr1, arr2, merged, i + 1, j, k + 1);
        } else {
            merged[k] = arr2[j];
            mergeRecursive(arr1, arr2, merged, i, j + 1, k + 1);
        }
    }

    // 3. 遞迴尋找陣列中的第 k 小元素
    public static int findKthSmallest(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k 超出陣列範圍");
        }
        // k 是 1-based，索引是 0-based
        return findKthSmallestRecursive(arr, 0, arr.length - 1, k - 1);
    }
    
    private static int findKthSmallestRecursive(int[] arr, int low, int high, int kIndex) {
        if (low <= high) {
            int pivotIndex = partition(arr, low, high);
            if (pivotIndex == kIndex) {
                return arr[pivotIndex];
            }
            if (pivotIndex > kIndex) {
                return findKthSmallestRecursive(arr, low, pivotIndex - 1, kIndex);
            }
            return findKthSmallestRecursive(arr, pivotIndex + 1, high, kIndex);
        }
        // 理論上不應該執行到這裡，如果 k 在有效範圍內
        return -1;
    }


    // 4. 遞迴檢查是否存在子序列總和等於目標值
    public static boolean subsetSumExists(int[] arr, int target) {
        return subsetSumRecursive(arr, target, arr.length - 1);
    }

    private static boolean subsetSumRecursive(int[] arr, int target, int n) {
        // 基礎情況
        if (target == 0) {
            return true;
        }
        if (n < 0 || target < 0) {
            return false;
        }

        // 遞迴步驟
        // 選擇 1: 包含目前元素 arr[n]
        boolean include = subsetSumRecursive(arr, target - arr[n], n - 1);
        
        // 選擇 2: 不包含目前元素 arr[n]
        boolean exclude = subsetSumRecursive(arr, target, n - 1);

        return include || exclude;
    }
}