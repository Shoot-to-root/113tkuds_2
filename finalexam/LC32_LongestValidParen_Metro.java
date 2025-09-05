
import java.util.*;

public class LC32_LongestValidParen_Metro {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int result = longestValidParentheses(s);

        System.out.println(result);
    }

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始化基準點
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // 把索引壓入棧中
                stack.push(i);
            } else {
                // 先彈出一個元素
                stack.pop();

                if (stack.isEmpty()) {
                    // 若棧空，表示無法匹配，更新基準點
                    stack.push(i);
                } else {
                    // 當前合法長度 = i - 棧頂索引
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
