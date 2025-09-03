
class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // 呼叫回溯函數，從空字串和0個左、右括號開始
        backtrack(result, "", 0, 0, n);
        return result;
    }

    /**
     * @param result 儲存所有有效組合的列表
     * @param currentCombination 當前的組合字串
     * @param open 已使用的左括號數量
     * @param close 已使用的右括號數量
     * @param max 允許使用的括號對數
     */
    private void backtrack(List<String> result, String currentCombination, int open, int close, int max) {
        // 如果組合的長度達到 2 * max，表示一個完整且有效的組合已生成
        if (currentCombination.length() == 2 * max) {
            result.add(currentCombination);
            return;
        }

        // 已使用的左括號數量必須小於總對數 n, 添加左括號
        if (open < max) {
            backtrack(result, currentCombination + "(", open + 1, close, max);
        }

        // 條件：已使用的右括號數量必須小於已使用的左括號數量, 添加右括號
        if (close < open) {
            backtrack(result, currentCombination + ")", open, close + 1, max);
        }
    }
}
