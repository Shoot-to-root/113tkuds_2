
class Solution {

    public int removeElement(int[] nums, int val) {
        // 處理邊界情況
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 設置雙指針，slow 指針用來追蹤非 val 元素的位置
        int slow = 0;

        // fast 指針遍歷整個陣列
        for (int fast = 0; fast < nums.length; fast++) {
            // 如果 fast 指針所指的元素不等於 val
            if (nums[fast] != val) {
                // 將這個元素複製到 slow 指針的位置
                nums[slow] = nums[fast];
                // slow 指針向前移動，準備接收下一個非 val 元素
                slow++;
            }
        }

        // 返回新陣列的長度, slow 指針的位置就是新長度
        return slow;
    }
}
