package library;
/**
 * @author Michael Carson 
 * 
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BoardGameTests {
    private BoardGame boardGame;

    @Before
    public void setUp() {
        // This will run before each test
        boardGame = BoardGame.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        BoardGame instance1 = BoardGame.getInstance();
        BoardGame instance2 = BoardGame.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testStartGame() {
        boardGame.startGame();
        // Add assertions based on what startGame is supposed to do
        assertNotNull(boardGame.getInstance());
    }

    @Test
    public void testNextSpace() {
        boardGame.nextSpace(5);
        // Add assertions based on what nextSpace is supposed to do
        assertEquals(expectedPlayerPosition, boardGame.getPlayer().getPosition());
    }

    @Test
    public void testLoadGame() {
        int progress = BoardGame.loadGame(0);
        assertEquals(0, progress);
    }

    @Test
    public void testSaveBoard() {
        int result = BoardGame.saveBoard();
        assertEquals(0, result);
    }
}
