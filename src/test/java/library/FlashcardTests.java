package library;
/**
 * @author Michael Carson 
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class FlashcardTests {
    private Flashcard flashcard;
    private Word word;

    @Before
    public void setUp() {
        // This will run before each test
        word = new Word("foreignWord", "englishWord");
        flashcard = new Flashcard(word);
    }

    @Test
    public void testFlipCard() {
        // Initially, the flashcard is not flipped
        assertEquals("foreignWord", flashcard.flipCard());

        // After one flip, it should show the back
        assertEquals("englishWord", flashcard.flipCard());

        // After another flip, it should show the front again
        assertEquals("foreignWord", flashcard.flipCard());
    }

    @Test
    public void testGetCurrentWord() {
        assertEquals(word, flashcard.getCurrentWord());
    }

    @Test
    public void testGetContent() {
        ArrayList<Word> expectedContent = new ArrayList<>();
        expectedContent.add(word);
        assertEquals(expectedContent, flashcard.getContent());
    }
}

