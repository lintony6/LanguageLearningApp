/**
 * This class implements the flashcard lesson type
 * @author Michael Carson 
 */
public class Flashcard {
    private Word front;
    private Word back;
    private boolean isFlipped;

    /**
     * initializes flashcard 
     * @param front
     * @param back
     */
    public Flashcard(Word front, Word back) {
        this.front = front;
        this.back = back;
        this.isFlipped = false;
    }
    /**
     * sets the front of the flashcard 
     * @param word
     * @return
     */
    public boolean setFront(Word word) {
        this.front = word;
        return true;
    }
    /**
     * sets the back of the flashcard 
     * @param word
     * @return
     */
    public boolean setBack(Word word) {
        this.back = word;
        return true;
    }
    /**
     * flips the card 
     */
    public void flipCard() {
        isFlipped = !isFlipped;
    }
    /**
     * returns the current word based on weather or not the card is flipped
     * @return
     */
    public Word getCurrentWord() {
        return isFlipped ? back : front;
    }
}

