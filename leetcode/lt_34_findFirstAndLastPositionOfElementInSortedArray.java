
class Solution {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        // 尋找第一個出現的位置
        int firstOccurrence = findBoundary(nums, target, true);
        if (firstOccurrence == -1) {
            return result; // 如果找不到，直接返回 [-1, -1]
        }

        // 尋找最後一個出現的位置
        int lastOccurrence = findBoundary(nums, target, false);

        result[0] = firstOccurrence;
        result[1] = lastOccurrence;

        return result;
    }

    /**
     * 輔助函數：使用二分搜尋法尋找目標值的邊界位置
     *
     * @param nums 已排序的陣列
     * @param target 目標值
     * @param isFindingFirst 是否尋找第一個出現的位置 (true) 或最後一個 (false)
     * @return 找到的索引，如果找不到則返回 -1
     */
    private int findBoundary(int[] nums, int target, boolean isFindingFirst) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid; // 儲存當前找到的索引

                if (isFindingFirst) {
                    // 如果尋找第一個，繼續在左半部分搜尋
                    right = mid - 1;
                } else {
                    // 如果尋找最後一個，繼續在右半部分搜尋
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;
    }
}
