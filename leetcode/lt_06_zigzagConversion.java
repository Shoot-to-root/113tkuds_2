
class Solution {

    public String convert(String s, int numRows) {
        // 如果行數為 1，之字形排列沒有意義，直接返回原字串
        if (numRows == 1) {
            return s;
        }

        // 創建一個列表，每個元素代表一行，用來儲存字元
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int currentRow = 0;
        boolean isGoingDown = false;

        // 遍歷輸入字串中的每個字元
        for (char c : s.toCharArray()) {
            // 將字元添加到當前行
            rows.get(currentRow).append(c);

            // 如果到達第一行或最後一行，改變方向
            if (currentRow == 0 || currentRow == numRows - 1) {
                isGoingDown = !isGoingDown;
            }

            // 根據方向移動到下一行
            if (isGoingDown) {
                currentRow++;
            } else {
                currentRow--;
            }
        }

        // 將所有行的內容拼接起來
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
