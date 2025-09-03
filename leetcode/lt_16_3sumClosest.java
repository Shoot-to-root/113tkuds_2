class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 對陣列進行排序
        Arrays.sort(nums);
        
        // 初始化最接近的總和，使用陣列前三個元素的和作為初始值
        int closestSum = nums[0] + nums[1] + nums[2];

        // 遍歷範圍到 nums.length - 2，以確保 left 和 right 指針有足夠的空間
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                // 比較總和並更新最接近的總和
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
                
                // 根據總和與目標的關係移動指針
                if (sum > target) {
                    // 總和太大，需要減小它，移動右指針向左
                    right--;
                } else if (sum < target) {
                    // 總和太小，需要增加它，移動左指針向右
                    left++;
                } else {
                    // 總和等於目標，這是最接近的，直接返回
                    return sum;
                }
            }
        }
        
        return closestSum;
    }
}