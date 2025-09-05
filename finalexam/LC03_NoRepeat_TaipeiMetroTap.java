
import java.util.HashMap;
import java.util.Scanner;

public class LC03_NoRepeat_TaipeiMetroTap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int result = lengthOfLongestSubstring(s);

        System.out.println(result);
    }

    // 找出最長不含重複字元的子字串長度
    public static int lengthOfLongestSubstring(String s) {
        // 使用 HashMap 記錄：字元 -> 最近一次出現的索引
        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果該字元之前出現過，且索引 >= 當前左界
            // 更新左界到該字元上次出現位置 +1
            if (map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;
            }

            // 更新字元最新出現位置
            map.put(c, right);

            // 更新答案：視窗長度 = right - left + 1
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
