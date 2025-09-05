
import java.util.*;

public class LC28_StrStr_NoticeSearch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String haystack = sc.nextLine();
        String needle = sc.nextLine();
        sc.close();

        int index = strStr(haystack, needle);

        System.out.println(index);
    }

    /**
     * 找出子字串 needle 在 haystack 中首次出現的索引 若不存在，回傳 -1 若 needle 為空字串，依題意回傳 0
     */
    public static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        // 特殊情況：needle 空字串 -> 回傳 0
        if (m == 0) {
            return 0;
        }

        // 若 needle 比 haystack 還長 -> 不可能找到
        if (m > n) {
            return -1;
        }

        // 暴力解法：枚舉 haystack 每個起點
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            // 嘗試匹配 needle
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            // 若 j == m，表示完整匹配
            if (j == m) {
                return i;
            }
        }

        // 沒找到
        return -1;
    }
}
