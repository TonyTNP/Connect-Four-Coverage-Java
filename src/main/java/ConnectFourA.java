public class ConnectFourA {

    public enum Result { INVALID, OK, WIN, DRAW }

    private static final int ROWS = 6;
    private static final int COLS = 7;

    private final char[][] board = new char[ROWS][COLS];
    private char currentPlayer = 'R';
    private int moves = 0;

    public ConnectFourA() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = '.';
            }
        }
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public Result makeMove(int col) {

        if (col < 0 || col >= COLS) {
            return Result.INVALID;
        }

        int row = findDropRow(col);

        if (row == -1) {
            return Result.INVALID;
        }

        board[row][col] = currentPlayer;
        moves++;

        if (checkWinFrom(row, col)) {
            return Result.WIN;
        }

        if (moves == ROWS * COLS) {
            return Result.DRAW;
        }

        togglePlayer();
        return Result.OK;
    }

    private int findDropRow(int col) {
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == '.') {
                return r;
            }
        }
        return -1;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
    }

    private boolean checkWinFrom(int row, int col) {

        char p = board[row][col];

        return
                countInDirection(row, col, 0, 1, p)
                        + countInDirection(row, col, 0, -1, p) - 1 >= 4 ||

                        countInDirection(row, col, 1, 0, p)
                                + countInDirection(row, col, -1, 0, p) - 1 >= 4 ||

                        countInDirection(row, col, 1, 1, p)
                                + countInDirection(row, col, -1, -1, p) - 1 >= 4 ||

                        countInDirection(row, col, 1, -1, p)
                                + countInDirection(row, col, -1, 1, p) - 1 >= 4;
    }

    private int countInDirection(int row, int col, int dr, int dc, char p) {

        int count = 0;

        int r = row;
        int c = col;

        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == p) {
            count++;
            r += dr;
            c += dc;
        }

        return count;
    }
}