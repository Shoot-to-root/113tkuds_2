
import java.util.*;

public class LC20_ValidParentheses_AlertFormat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        // 建立閉括號對應的開括號
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : s.toCharArray()) {
            // 如果是閉括號，檢查是否能與棧頂配對
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false; // 不匹配, 提早返回
                }
            } else {
                // 否則是開括號，推入棧中
                stack.push(c);
            }
        }

        // 最後棧必須為空，否則有未配對的開括號
        return stack.isEmpty();
    }
}
