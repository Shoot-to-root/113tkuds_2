
class Solution {

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 尋找一個空的單元格
                if (board[i][j] == '.') {
                    // 嘗試填入數字 1-9
                    for (char c = '1'; c <= '9'; c++) {
                        // 如果當前數字有效
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // 選擇：填入數字

                            // 遞迴地解決下一個單元格
                            if (solve(board)) {
                                return true; // 如果解決成功，返回 true
                            } else {
                                board[i][j] = '.'; // 回溯：如果解決失敗，撤銷當前選擇
                            }
                        }
                    }
                    // 如果所有數字都嘗試過且無解，則返回 false
                    return false;
                }
            }
        }
        // 如果所有單元格都已填滿，表示數獨已解決
        return true;
    }

    // 輔助函數：檢查在指定位置填入數字是否有效
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 檢查行
            if (board[row][i] == c) {
                return false;
            }
            // 檢查列
            if (board[i][col] == c) {
                return false;
            }
            // 檢查 3x3 子宮格
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
