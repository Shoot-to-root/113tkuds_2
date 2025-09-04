
class Solution {

    public int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();

        // 將 -1 壓入堆疊作為基底，方便計算長度
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // 如果是開括號，將其索引壓入堆疊
                stack.push(i);
            } else { // c == ')'
                // 遇到閉括號，彈出堆疊頂部元素
                stack.pop();

                if (stack.isEmpty()) {
                    // 如果堆疊為空，表示當前閉括號沒有匹配的開括號，將當前索引作為新的基底
                    stack.push(i);
                } else {
                    // 如果堆疊不為空，計算有效子字串的長度
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
}
