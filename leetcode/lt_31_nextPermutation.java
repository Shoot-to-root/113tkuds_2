class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;

        // 從右到左找到第一個非升序的元素
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 如果找到了這樣的元素 (i >= 0)
        if (i >= 0) {
            int j = nums.length - 1;
            // 從右到左找到第一個比 nums[i] 大的元素
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 交換這兩個元素
            swap(nums, i, j);
        }

        // 反轉從 i+1 到陣列末尾的部分
        // 如果整個陣列都是降序，i 會是 -1，這將反轉整個陣列
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}