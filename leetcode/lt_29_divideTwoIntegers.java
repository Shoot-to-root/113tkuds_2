
class Solution {

    public int divide(int dividend, int divisor) {
        // 處理溢位情況，只有一種情況會溢位
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 確定最終結果的符號
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // 將被除數和除數都轉換為負數，避免 Integer.MIN_VALUE 轉正數時的溢位
        int negativeDividend = dividend > 0 ? -dividend : dividend;
        int negativeDivisor = divisor > 0 ? -divisor : divisor;

        int result = 0;

        // 使用位元運算模擬除法
        while (negativeDividend <= negativeDivisor) {
            int tempDivisor = negativeDivisor;
            int multiple = 1;

            // 尋找最大的 i，使得 (divisor << i) 不會超過 dividend
            // 使用 tempDivisor >= (Integer.MIN_VALUE >> 1) 來防止 tempDivisor 溢位
            while (negativeDividend <= (tempDivisor << 1) && tempDivisor >= (Integer.MIN_VALUE >> 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            // 執行模擬減法
            negativeDividend -= tempDivisor;
            result += multiple;
        }

        // 根據符號返回最終結果
        if (isNegative) {
            return -result;
        } else {
            return result;
        }
    }
}
