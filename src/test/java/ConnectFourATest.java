import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectFourATest {

    @Test
    void invalidColumnTooSmall() {
        ConnectFourA g = new ConnectFourA();
        assertEquals(ConnectFourA.Result.INVALID, g.makeMove(-1));
        assertEquals('R', g.getCurrentPlayer());
    }

    @Test
    void invalidColumnTooLarge() {
        ConnectFourA g = new ConnectFourA();
        assertEquals(ConnectFourA.Result.INVALID, g.makeMove(7));
        assertEquals('R', g.getCurrentPlayer());
    }

    @Test
    void fullColumnInvalid() {
        ConnectFourA g = new ConnectFourA();
        for (int i = 0; i < 6; i++) {
            assertNotEquals(ConnectFourA.Result.INVALID, g.makeMove(0));
        }
        assertEquals(ConnectFourA.Result.INVALID, g.makeMove(0));
    }

    @Test
    void horizontalWinForR() {
        ConnectFourA g = new ConnectFourA();

        g.makeMove(0);
        g.makeMove(6);
        g.makeMove(1);
        g.makeMove(6);
        g.makeMove(2);
        g.makeMove(6);

        assertEquals(ConnectFourA.Result.WIN, g.makeMove(3));
    }

    @Test
    void verticalWinForR() {
        ConnectFourA g = new ConnectFourA();

        g.makeMove(0);
        g.makeMove(1);
        g.makeMove(0);
        g.makeMove(1);
        g.makeMove(0);
        g.makeMove(1);

        assertEquals(ConnectFourA.Result.WIN, g.makeMove(0));
    }

    @Test
    void diagonalDownRightWinForR() {
        ConnectFourA g = new ConnectFourA();

        g.makeMove(0);

        g.makeMove(1); g.makeMove(1);

        g.makeMove(2); g.makeMove(6);
        g.makeMove(2); g.makeMove(2);

        g.makeMove(3); g.makeMove(6);
        g.makeMove(3); g.makeMove(6);
        g.makeMove(3);

        assertEquals(ConnectFourA.Result.WIN, g.makeMove(3));
    }

    @Test
    void diagonalDownLeftWinForR() {
        ConnectFourA g = new ConnectFourA();

        g.makeMove(6);

        g.makeMove(5); g.makeMove(5);

        g.makeMove(4); g.makeMove(0);
        g.makeMove(4); g.makeMove(4);

        g.makeMove(3); g.makeMove(0);
        g.makeMove(3); g.makeMove(0);
        g.makeMove(3);

        assertEquals(ConnectFourA.Result.WIN, g.makeMove(3));
    }

    @Test
    void drawGame() {
        ConnectFourA g = new ConnectFourA();

        int[] moves = {
                3, 1, 0, 0, 1, 6, 2, 1, 6, 2, 5, 2, 1, 1,
                3, 2, 5, 5, 3, 0, 6, 5, 1, 5, 6, 3, 2, 6,
                6, 2, 5, 4, 3, 3, 0, 4, 4, 0, 4, 4, 0, 4
        };

        for (int i = 0; i < moves.length; i++) {
            ConnectFourA.Result r = g.makeMove(moves[i]);

            if (i < moves.length - 1) {
                assertEquals(ConnectFourA.Result.OK, r, "Move " + (i + 1) + " should be OK");
            } else {
                assertEquals(ConnectFourA.Result.DRAW, r, "Last move should produce DRAW");
            }
        }
    }}