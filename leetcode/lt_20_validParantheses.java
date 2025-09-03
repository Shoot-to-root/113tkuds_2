
class Solution {

    public boolean isValid(String s) {
        // 創建一個堆疊來儲存開括號
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 如果是開括號，推入堆疊
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } // 如果是閉括號
            else {
                // 檢查堆疊是否為空
                if (stack.isEmpty()) {
                    return false;
                }

                // 彈出堆疊頂端的開括號並進行配對
                char openBracket = stack.pop();
                if ((c == ')' && openBracket != '(')
                        || (c == '}' && openBracket != '{')
                        || (c == ']' && openBracket != '[')) {
                    return false;
                }
            }
        }

        // 檢查堆疊是否為空, 如果為空，表示所有括號都已正確配對
        return stack.isEmpty();
    }
}
