
class Solution {

    private int start = 0;
    private int maxLength = 0;

    public String longestPalindrome(String s) {
        // 如果字串長度小於 2，直接返回它本身
        if (s.length() < 2) {
            return s;
        }

        // 遍歷字串，將每個字元作為中心（奇數長度）和每對相鄰字元作為中心（偶數長度）
        for (int i = 0; i < s.length(); i++) {
            // 處理奇數長度的迴文（中心是 s[i]）
            expandFromCenter(s, i, i);

            // 處理偶數長度的迴文（中心是 s[i] 和 s[i+1]）
            expandFromCenter(s, i, i + 1);
        }

        // 根據最終的起始索引和長度，返回最長的迴文字串
        return s.substring(start, start + maxLength);
    }

    private void expandFromCenter(String s, int left, int right) {
        // 從中心向兩邊擴展，檢查是否是迴文
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // 計算當前迴文字串的長度
        int currentLength = right - left - 1;

        // 如果當前長度大於已知的最大長度，則更新
        if (currentLength > maxLength) {
            maxLength = currentLength;
            start = left + 1;
        }
    }
}
