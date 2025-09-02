
class Solution {

    public int myAtoi(String s) {
        int index = 0;
        int sign = 1; // 1 為正，-1 為負
        long result = 0; // 使用 long 類型來處理溢位檢查

        // 忽略前導空格
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }

        // 處理正負號
        if (index < s.length() && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }

        // 讀取數字並進行轉換
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';
            result = result * 10 + digit;

            // 檢查溢位
            if (sign * result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            index++;
        }

        // 返回最終結果
        return (int) (sign * result);
    }
}
