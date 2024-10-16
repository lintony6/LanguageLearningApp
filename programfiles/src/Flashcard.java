/**
 * This class implements the flashcard lesson type
 * @author Michael Carson 
 */
public class Flashcard {
private Word word;
private Word frontWord;
private Word backWord;
private Word front;
private Word back;
private boolean isFlipped;


public Flashcard(Word word){
this.word = word;
this.front = frontWord;
this.back = backWord;
this.isFlipped = false;
}
public boolean setFront(Word word){
    this.front = frontWord;
    return true;
}

public void flipCard(){
    isFlipped = !isFlipped;
}

public boolean setBack(Word word){
    this.back = backWord;
    return true;
}
}
