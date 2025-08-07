import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class NumberArrayProcessor {

    // 1. 移除陣列中的重複元素
    public static int[] removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("錯誤：陣列為空或 null。");
            return new int[0];
        }

        HashSet<Integer> uniqueElements = new HashSet<>();
        for (int element : arr) {
            uniqueElements.add(element);
        }

        int[] result = new int[uniqueElements.size()];
        int index = 0;
        for (int element : uniqueElements) {
            result[index++] = element;
        }

        return result;
    }

    // 2. 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            System.out.println("錯誤：輸入陣列不能為 null。");
            return null;
        }

        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            merged[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            merged[k++] = arr2[j++];
        }

        return merged;
    }

    // 3. 找出陣列中出現頻率最高的元素
    public static int findMostFrequent(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("錯誤：陣列為空或 null。");
            return -1; 
        }

        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int element : arr) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        int mostFrequentElement = arr[0];
        int maxFrequency = 0;

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentElement = entry.getKey();
            }
        }
        return mostFrequentElement;
    }

    // 4. 將陣列分割成兩個相等（或近似相等）的子陣列
    public static int[][] splitArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("錯誤：陣列為空或 null。");
            return new int[0][0];
        }

        int middle = arr.length / 2;
        int[] subArray1 = Arrays.copyOfRange(arr, 0, middle);
        int[] subArray2 = Arrays.copyOfRange(arr, middle, arr.length);

        return new int[][]{subArray1, subArray2};
    }

    public static void main(String[] args) {
        
        System.out.println("====== 1. 移除陣列中的重複元素 ======");
        int[] arrWithDuplicates = {1, 5, 2, 5, 3, 1, 4, 3, 5};
        System.out.println("原始陣列: " + Arrays.toString(arrWithDuplicates));
        int[] uniqueArr = removeDuplicates(arrWithDuplicates);
        System.out.println("移除重複後: " + Arrays.toString(uniqueArr));
        System.out.println("------------------------------------");

        System.out.println("====== 2. 合併兩個已排序的陣列 ======");
        int[] sortedArr1 = {1, 3, 5, 7};
        int[] sortedArr2 = {2, 4, 6, 8};
        System.out.println("陣列一: " + Arrays.toString(sortedArr1));
        System.out.println("陣列二: " + Arrays.toString(sortedArr2));
        int[] mergedArr = mergeSortedArrays(sortedArr1, sortedArr2);
        System.out.println("合併後: " + Arrays.toString(mergedArr));
        System.out.println("------------------------------------");

        System.out.println("====== 3. 找出陣列中出現頻率最高的元素 ======");
        int[] frequencyArr = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        System.out.println("頻率陣列: " + Arrays.toString(frequencyArr));
        int mostFrequent = findMostFrequent(frequencyArr);
        System.out.println("頻率最高的元素是: " + mostFrequent);
        System.out.println("------------------------------------");

        System.out.println("====== 4. 將陣列分割成兩個相等（或近似相等）的子陣列 ======");
        int[] arrayToSplit = {10, 20, 30, 40, 50, 60, 70};
        System.out.println("原始陣列: " + Arrays.toString(arrayToSplit));
        int[][] splitArrays = splitArray(arrayToSplit);
        if (splitArrays.length > 0) {
            System.out.println("子陣列一: " + Arrays.toString(splitArrays[0]));
            System.out.println("子陣列二: " + Arrays.toString(splitArrays[1]));
        }
    }
}