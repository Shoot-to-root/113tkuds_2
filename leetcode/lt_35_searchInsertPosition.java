
class Solution {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 使用二分搜尋法
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // 如果找到目標，返回它的索引
                return mid;
            } else if (nums[mid] < target) {
                // 如果中間值小於目標，向右半部分尋找
                left = mid + 1;
            } else {
                // 如果中間值大於目標，向左半部分尋找
                right = mid - 1;
            }
        }

        // 如果迴圈結束仍未找到，left 指針就是目標應該被插入的位置
        return left;
    }
}
