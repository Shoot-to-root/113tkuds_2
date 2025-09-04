
class Solution {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判斷左半部分 [left, mid] 是否有序
            if (nums[left] <= nums[mid]) {
                // 如果左半部分有序
                // 檢查目標是否在左半部分
                if (target >= nums[left] && target < nums[mid]) {
                    // 如果在，則在左半部分搜尋
                    right = mid - 1;
                } else {
                    // 否則，在右半部分搜尋
                    left = mid + 1;
                }
            } else {
                // 否則右半部分 [mid, right] 有序
                // 檢查目標是否在右半部分
                if (target > nums[mid] && target <= nums[right]) {
                    // 如果在，則在右半部分搜尋
                    left = mid + 1;
                } else {
                    // 否則，在左半部分搜尋
                    right = mid - 1;
                }
            }
        }

        // 如果找不到，返回 -1
        return -1;
    }
}
