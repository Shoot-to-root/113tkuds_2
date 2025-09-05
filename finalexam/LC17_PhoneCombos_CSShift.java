
import java.util.Scanner;

public class LC17_PhoneCombos_CSShift {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String digits = "";
        if (sc.hasNextLine()) {
            digits = sc.nextLine().trim();
        }

        if (digits.isEmpty()) {
            return;
        }

        // 電話鍵盤映射：對應 '2'..'9'
        String[] map = new String[]{
            "abc", // '2'
            "def", // '3'
            "ghi", // '4'
            "jkl", // '5'
            "mno", // '6'
            "pqrs", // '7'
            "tuv", // '8'
            "wxyz" // '9'
        };

        // 使用 StringBuilder 做回溯，用以避免大量字串分配
        StringBuilder sb = new StringBuilder();

        // 開始回溯並直接逐行輸出每一個組合（按照數字順序與鍵上字母順序生成）
        backtrack(digits, map, 0, sb);
    }

    // 遞迴生成所有字母組合，當 idx 到達 digits.length() 時直接 System.out.println(sb.toString())
    private static void backtrack(String digits, String[] map, int idx, StringBuilder sb) {
        if (idx == digits.length()) {
            // 到達深度，列印一組完整組合
            System.out.println(sb.toString());
            return;
        }

        char d = digits.charAt(idx);
        // 假設題目保證 d 在 '2'..'9'，若要更保險可檢查範圍
        int mapIndex = d - '2';
        if (mapIndex < 0 || mapIndex >= map.length) {
            // 非法字元（依題意不應發生），這裡直接跳過
            return;
        }

        String letters = map[mapIndex];
        for (int k = 0; k < letters.length(); k++) {
            sb.append(letters.charAt(k));
            backtrack(digits, map, idx + 1, sb);
            sb.deleteCharAt(sb.length() - 1); // 回溯：移除最後一個字元
        }
    }
}
