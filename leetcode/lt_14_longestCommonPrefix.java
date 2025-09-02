
class Solution {

    public String longestCommonPrefix(String[] strs) {
        // 處理邊界情況。如果陣列為空，則沒有共同前綴
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以第一個字串作為初始的共同前綴
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短前綴，直到它成為當前字串的前綴為止
            // indexOf(prefix) == 0 表示 strs[i] 以 prefix 開頭
            while (strs[i].indexOf(prefix) != 0) {
                // 如果不以當前前綴開頭，就將前綴的最後一個字元移除。
                prefix = prefix.substring(0, prefix.length() - 1);

                // 如果前綴被縮短到空字串，表示沒有共同前綴，直接返回
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }
}
