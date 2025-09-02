
class Solution {

    public int reverse(int x) {
        // 使用 long 類型來儲存反轉後的數字，以處理溢位
        long reversedNum = 0;

        while (x != 0) {
            // 取得 x 的最後一個數字
            int digit = x % 10;
            // 將這個數字添加到 reversedNum 的末尾
            reversedNum = reversedNum * 10 + digit;
            // 移除 x 的最後一個數字
            x /= 10;
        }

        // 檢查反轉後的數字是否溢位
        if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
            return 0;
        }

        // 將 long 類型轉換為 int 類型並返回
        return (int) reversedNum;
    }
}
