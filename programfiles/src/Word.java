import java.util.ArrayList;

/**
 * The Word class in Java represents a word with its foreign and
 * English translations, along with a list of similar words.
 * @author Tony Lin
 */
public class Word {
  private String foreign;
  private String english;
  //private WordType type;
  private ArrayList<Word> similar;

  public Word(String foreign, String english) {//WordType type) {
    this.foreign = foreign;
    this.english = english;
    //this.type = type;
    similar = new ArrayList<Word>();
  }

  public boolean isCorrect(String foreign, String english){
    if(this.foreign.equals(foreign) && this.english.equals(english))
      return true;
    return false;
  }

//   public WordType getType() {
//     return this.type;
//   }

  public String getForeign() {
    return this.foreign;
  }

  public String getEnglish() {
    return this.english;
  }

}
