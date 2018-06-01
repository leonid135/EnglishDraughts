import org.junit.jupiter.api.Test;
import task3.Logic.Game;
import task3.Logic.Turns;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTests {
    @Test
    public void JumpTest() {
        Game game = new Game();
        //black
        game.makeTurn(5, 2, Turns.RIGHT);
        //white
        game.makeTurn(2, 5, Turns.RIGHT);

        assertEquals(0, game.getBlackScore());

        game.makeTurn(4, 3, Turns.RIGHT);

        assertEquals(1, game.getBlackScore());
    }

    @Test
    public void ManToKingTest() {
        Game game = new Game();
        //black to king
        game.makeTurn(5, 2, Turns.RIGHT);
        game.makeTurn(2, 5, Turns.RIGHT);
        game.makeTurn(4, 3, Turns.RIGHT);
        game.makeTurn(4, 5, Turns.RIGHT);
        game.makeTurn(3, 6, Turns.RIGHT);
        game.makeTurn(4, 7, Turns.LEFT);


        game.makeTurn(2, 5, Turns.LEFT);

        assertEquals(-2, game.getCheckerAt(4, 7));
    }
}
