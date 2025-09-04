
class Solution {

    public String countAndSay(int n) {
        // 初始化，n=1 的情況
        String s = "1";

        // 迴圈 n-1 次來生成序列
        for (int i = 2; i <= n; i++) {
            StringBuilder nextString = new StringBuilder();
            int count = 1;
            char currentChar = s.charAt(0);

            // 遍歷當前字串來生成下一個字串
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) == currentChar) {
                    count++;
                } else {
                    nextString.append(count).append(currentChar);
                    currentChar = s.charAt(j);
                    count = 1;
                }
            }

            // 處理最後一個連續字元組
            nextString.append(count).append(currentChar);

            // 更新字串
            s = nextString.toString();
        }

        // 返回最終結果
        return s;
    }
}
