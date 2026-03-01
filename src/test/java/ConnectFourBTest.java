import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectFourBTest {

    @Test
    void invalidColumnTooSmall() {
        ConnectFourB g = new ConnectFourB();
        assertEquals(ConnectFourB.Result.INVALID, g.makeMove(-1));
        assertEquals('R', g.getCurrentPlayer());
    }

    @Test
    void invalidColumnTooLarge() {
        ConnectFourB g = new ConnectFourB();
        assertEquals(ConnectFourB.Result.INVALID, g.makeMove(7));
        assertEquals('R', g.getCurrentPlayer());
    }

    @Test
    void fullColumnInvalid() {
        ConnectFourB g = new ConnectFourB();
        for (int i = 0; i < 6; i++) {
            assertNotEquals(ConnectFourB.Result.INVALID, g.makeMove(0));
        }
        assertEquals(ConnectFourB.Result.INVALID, g.makeMove(0));
    }

    @Test
    void horizontalWinForR() {
        ConnectFourB g = new ConnectFourB();

        g.makeMove(0);
        g.makeMove(6);
        g.makeMove(1);
        g.makeMove(6);
        g.makeMove(2);
        g.makeMove(6);

        assertEquals(ConnectFourB.Result.WIN, g.makeMove(3));
    }

    @Test
    void verticalWinForR() {
        ConnectFourB g = new ConnectFourB();

        g.makeMove(0);
        g.makeMove(1);
        g.makeMove(0);
        g.makeMove(1);
        g.makeMove(0);
        g.makeMove(1);

        assertEquals(ConnectFourB.Result.WIN, g.makeMove(0));
    }

    @Test
    void diagonalDownRightWinForR() {
        ConnectFourB g = new ConnectFourB();

        g.makeMove(0);

        g.makeMove(1); g.makeMove(1);

        g.makeMove(2); g.makeMove(6);
        g.makeMove(2); g.makeMove(2);

        g.makeMove(3); g.makeMove(6);
        g.makeMove(3); g.makeMove(6);
        g.makeMove(3);

        assertEquals(ConnectFourB.Result.WIN, g.makeMove(3));
    }

    @Test
    void diagonalDownLeftWinForR() {
        ConnectFourB g = new ConnectFourB();

        g.makeMove(6);

        g.makeMove(5); g.makeMove(5);

        g.makeMove(4); g.makeMove(0);
        g.makeMove(4); g.makeMove(4);

        g.makeMove(3); g.makeMove(0);
        g.makeMove(3); g.makeMove(0);
        g.makeMove(3);

        assertEquals(ConnectFourB.Result.WIN, g.makeMove(3));
    }

    @Test
    void drawGame() {
        ConnectFourB g = new ConnectFourB();

        int[] moves = {
                3, 1, 0, 0, 1, 6, 2, 1, 6, 2, 5, 2, 1, 1,
                3, 2, 5, 5, 3, 0, 6, 5, 1, 5, 6, 3, 2, 6,
                6, 2, 5, 4, 3, 3, 0, 4, 4, 0, 4, 4, 0, 4
        };

        for (int i = 0; i < moves.length; i++) {
            ConnectFourB.Result r = g.makeMove(moves[i]);

            if (i < moves.length - 1) {
                assertEquals(ConnectFourB.Result.OK, r, "Move " + (i + 1) + " should be OK");
            } else {
                assertEquals(ConnectFourB.Result.DRAW, r, "Last move should produce DRAW");
            }
        }
    }
}