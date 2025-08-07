import java.util.Arrays;

public class TicTacToeBoard {

    private static char[][] board;
    private static final int BOARD_SIZE = 3;

    // 初始化 3x3 的遊戲棋盤
    public TicTacToeBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], ' ');
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.printf(" %c |", board[i][j]);
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // 實作放置棋子的功能（檢查位置是否有效）
    public boolean placeMark(int row, int col, char player) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            System.out.println("錯誤：無效的行或列，請輸入 0-2 範圍內的數字。");
            return false;
        }

        if (board[row][col] != ' ') {
            System.out.println("錯誤：該位置已被佔用，請選擇其他位置。");
            return false;
        }

        board[row][col] = player;
        return true;
    }

    // 檢查獲勝條件（行、列、對角線）
    public boolean checkWin(char player) {
        // 檢查行和列
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true; // 檢查行
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true; // 檢查列
        }

        // 檢查對角線
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true; // 主對角線
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true; // 反對角線

        return false;
    }

    // 判斷遊戲是否結束（獲勝或平手）
    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false; // 還有空位
                }
            }
        }
        return true; // 棋盤已滿
    }

    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        char currentPlayer = 'X';
        boolean gameOver = false;

        System.out.println("=== 遊戲開始 ===");
        game.printBoard();

        if (game.placeMark(0, 0, 'X')) System.out.println("玩家 X 在 (0, 0) 放置棋子。");
        game.printBoard();
        if (game.placeMark(1, 1, 'O')) System.out.println("玩家 O 在 (1, 1) 放置棋子。");
        game.printBoard();
        if (game.placeMark(0, 1, 'X')) System.out.println("玩家 X 在 (0, 1) 放置棋子。");
        game.printBoard();
        if (game.placeMark(2, 2, 'O')) System.out.println("玩家 O 在 (2, 2) 放置棋子。");
        game.printBoard();
        if (game.placeMark(0, 2, 'X')) System.out.println("玩家 X 在 (0, 2) 放置棋子。");
        game.printBoard();

        if (game.checkWin('X')) {
            System.out.println("恭喜！玩家 X 獲勝！");
            gameOver = true;
        } else if (game.isBoardFull()) {
            System.out.println("遊戲平手！");
            gameOver = true;
        }
        
        // 示範無效的移動
        System.out.println("\n=== 無效移動範例 ===");
        System.out.println("嘗試在 (0, 0) 放置棋子...");
        game.placeMark(0, 0, 'O');
        System.out.println("嘗試在 (-1, 0) 放置棋子...");
        game.placeMark(-1, 0, 'O');
    }
}