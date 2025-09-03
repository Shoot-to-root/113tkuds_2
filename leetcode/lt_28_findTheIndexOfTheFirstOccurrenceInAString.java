
class Solution {

    public int strStr(String haystack, String needle) {
        // 處理邊界情況。如果 needle 為空，返回 0
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int m = haystack.length();
        int n = needle.length();

        // 遍歷 haystack，從第一個字元開始
        // 迴圈範圍是 m - n，以確保有足夠的長度進行匹配
        for (int i = 0; i <= m - n; i++) {
            // Step 3: 檢查當前子字串是否與 needle 匹配
            int j;
            for (j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    // 如果不匹配，中斷內層迴圈
                    break;
                }
            }
            // 如果內層迴圈完全遍歷完（即 j 等於 n），則表示找到匹配
            if (j == n) {
                return i;
            }
        }

        // 如果迴圈結束仍未找到匹配，返回 -1
        return -1;
    }
}
