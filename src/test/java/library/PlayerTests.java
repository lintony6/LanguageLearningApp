package library;
/**
 * @author Michael Carson 
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    private Player player;

    @Before
    public void setUp() {
        // This will run before each test
        player = new Player("X");
    }

    @Test
    public void testInitialPosition() {
        assertEquals(0, player.getRow());
        assertEquals(0, player.getCol());
    }

    @Test
    public void testMoveWithinRow() {
        player.move(5, 15, 15);
        assertEquals(0, player.getRow());
        assertEquals(5, player.getCol());
    }

    @Test
    public void testMoveToNextRow() {
        player.move(15, 15, 15);
        assertEquals(1, player.getRow());
        assertEquals(0, player.getCol());
    }

    @Test
    public void testMoveToNextRowAndBeyond() {
        player.move(20, 15, 15);
        assertEquals(1, player.getRow());
        assertEquals(5, player.getCol());
    }

    @Test
    public void testMoveToLastCell() {
        player.move(224, 15, 15); // 15x15 grid has 225 cells (0 to 224)
        assertEquals(14, player.getRow());
        assertEquals(14, player.getCol());
    }

    @Test
    public void testMoveBeyondLastCell() {
        player.move(230, 15, 15); // Attempt to move beyond the last cell
        assertEquals(14, player.getRow());
        assertEquals(14, player.getCol());
    }

    @Test
    public void testGetSymbol() {
        assertEquals("X", player.getSymbol());
    }
}

