
class Solution {

    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 檢查兩個數字的總和是否等於目標值
                if (nums[i] + nums[j] == target) {
                    // 如果找到，返回它們的索引
                    return new int[]{i, j};
                }
            }
        }
        // 如果沒有找到任何組合，返回一個空陣列
        return new int[]{};
    }
}
