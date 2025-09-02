
class Solution {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] 代表 s 的前 i 個字元是否匹配 p 的前 j 個字元
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 基本情況：空字串匹配空模式
        dp[0][0] = true;

        // 處理像 "a*", "a*b*" 這種可以匹配空字串的模式
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填充 DP 表格
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果是直接匹配或 '.'
                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } // 如果是 '*'
                else if (p.charAt(j - 1) == '*') {
                    // 第一種情況: '*' 匹配零個前面的字元
                    dp[i][j] = dp[i][j - 2];

                    // 第二種情況: '*' 匹配一個或多個前面的字元
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        // 返回最終的匹配結果
        return dp[m][n];
    }
}
