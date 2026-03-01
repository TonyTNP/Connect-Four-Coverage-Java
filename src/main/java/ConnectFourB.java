public class ConnectFourB {

    public enum Result { INVALID, OK, WIN, DRAW }

    private static final int ROWS = 6;
    private static final int COLS = 7;

    private final char[][] board;
    private char currentPlayer;
    private int moves;

    public ConnectFourB() {
        board = new char[ROWS][COLS];
        reset();
    }

    public void reset() {
        currentPlayer = 'R';
        moves = 0;

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

        if (!inRange(col)) return Result.INVALID;

        int dropRow = -1;

        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == '.') {
                dropRow = r;
                break;
            }
        }

        if (dropRow == -1) return Result.INVALID;

        place(dropRow, col, currentPlayer);
        moves++;

        boolean win =
                fourInLine(dropRow, col, 0, 1)  ||
                        fourInLine(dropRow, col, 1, 0)  ||
                        fourInLine(dropRow, col, 1, 1)  ||
                        fourInLine(dropRow, col, 1, -1);

        if (win) return Result.WIN;

        if (moves >= ROWS * COLS) return Result.DRAW;

        currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
        return Result.OK;
    }

    private boolean inRange(int col) {
        return col >= 0 && col < COLS;
    }

    private void place(int row, int col, char token) {
        board[row][col] = token;
    }

    private boolean fourInLine(int row, int col, int dr, int dc) {

        char p = board[row][col];

        int total = 1;
        total += countStep(row, col, dr, dc, p);
        total += countStep(row, col, -dr, -dc, p);

        return total >= 4;
    }

    private int countStep(int row, int col, int dr, int dc, char p) {

        int count = 0;

        int r = row + dr;
        int c = col + dc;

        while (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
            if (board[r][c] != p) break;

            count++;
            r += dr;
            c += dc;
        }

        return count;
    }
}