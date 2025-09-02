
class Solution {

    public boolean isPalindrome(int x) {
        // 處理負數和以 0 結尾的數字（除了 0 本身）
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        // 迴圈直到反轉後的數字大於或等於原始數字
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 比較兩個數字
        // 如果 x == revertedNumber，表示原始數字是偶數位數
        // 如果 x == revertedNumber / 10，表示原始數字是奇數位數（忽略中間的數字）
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
