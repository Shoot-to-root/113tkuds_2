
class Solution {

    public int removeDuplicates(int[] nums) {
        //  處理邊界情況
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 設置雙指針
        int slow = 0;
        // fast 指針從第二個元素開始遍歷
        for (int fast = 1; fast < nums.length; fast++) {
            // 如果 fast 指針所指的元素與 slow 指針所指的元素不相等，表示是一個新的唯一元素
            if (nums[fast] != nums[slow]) {
                // 將 slow 指針向前移動
                slow++;
                // 將新的唯一元素複製到 slow 指針的位置
                nums[slow] = nums[fast];
            }
            // 如果相等，fast 指針繼續移動，slow 指針不動
        }

        // 返回新陣列的長度
        // slow 指針是從 0 開始的索引，所以長度是 slow + 1
        return slow + 1;
    }
}
