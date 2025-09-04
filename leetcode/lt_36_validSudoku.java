
class Solution {

    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rowSets = new HashSet[9];
        Set<Character>[] colSets = new HashSet[9];
        Set<Character>[] boxSets = new HashSet[9];

        // 初始化所有集合
        for (int i = 0; i < 9; i++) {
            rowSets[i] = new HashSet<>();
            colSets[i] = new HashSet<>();
            boxSets[i] = new HashSet<>();
        }

        // 遍歷數獨棋盤
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentVal = board[i][j];

                // 如果當前格子為空，跳過
                if (currentVal == '.') {
                    continue;
                }

                //  檢查與追蹤
                int boxIndex = (i / 3) * 3 + (j / 3);

                // 檢查行、列和子宮格是否已包含該數字
                if (rowSets[i].contains(currentVal)
                        || colSets[j].contains(currentVal)
                        || boxSets[boxIndex].contains(currentVal)) {
                    return false; // 發現重複，無效
                }

                // 如果沒有重複，將數字添加到對應的集合中
                rowSets[i].add(currentVal);
                colSets[j].add(currentVal);
                boxSets[boxIndex].add(currentVal);
            }
        }

        // 遍歷結束，沒有發現無效情況，返回 true
        return true;
    }
}
