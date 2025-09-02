class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        [cite_start]int m = nums1.length; [cite: 1]
        [cite_start]int n = nums2.length; [cite: 2]
        int[] mergedArray = new int[m + n];
        [cite_start]int i = 0, j = 0, k = 0; [cite: 3]
        
        // 合併兩個陣列，直到其中一個遍歷完畢
        [cite_start]while (i < m && j < n) { [cite: 4]
            if (nums1[i] <= nums2[j]) {
                [cite_start]mergedArray[k++] = nums1[i++]; [cite: 5]
            } else {
                [cite_start]mergedArray[k++] = nums2[j++]; [cite: 6]
            }
        }
        
        // 將 nums1 中剩餘的元素複製到合併後的陣列
        while (i < m) {
            [cite_start]mergedArray[k++] = nums1[i++]; [cite: 7]
        }

        // 將 nums2 中剩餘的元素複製到合併後的陣列
        while (j < n) {
            [cite_start]mergedArray[k++] = nums2[j++]; [cite: 8]
        }

        [cite_start]int totalLength = m + n; [cite: 9]
        // 根據總長度判斷是奇數還是偶數
        if (totalLength % 2 == 1) {
            // 如果是奇數，中位數是中間的那個元素
            [cite_start]return (double) mergedArray[totalLength / 2]; [cite: 10]
        } else {
            // 如果是偶數，中位數是中間兩個元素的平均值
            [cite_start]int mid1 = mergedArray[totalLength / 2 - 1]; [cite: 11]
            int mid2 = mergedArray[totalLength / 2];
            return ((double) mid1 + mid2) / 2.0;
        }
    }
}