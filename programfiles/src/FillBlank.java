import java.util.ArrayList;

/**
 * The class UserList maintains a collection of Users using a 
 * HashMap with UUID as the key and the User as the value;
 * @author Tony Lin and Ishaan Cheema
 */

public class FillBlank {
  private Word answer;
  private ArrayList<Word> question;

  public FillBlank(ArrayList<Word> question, Word answer) {
    this.question = question;
    this.answer = answer;
  }

  public Word getAnswer() {
    return this.answer;
  }
  
  public ArrayList<Word> getContent() {
    return this.question;
  }
  
  public boolean isCorrect(Word userAnswer) {
    return this.answer.equals(userAnswer);
  }
  
  public boolean isCorrect(String foreignWord) {
    return this.answer.getForeign().equals(foreignWord);
  }

  public void addWordToQuestion(Word word) {
    this.question.add(word);
  }

  public boolean removeWordFromQuestion(Word word) {
    return this.question.remove(word);
  }

  public void setAnswer(Word newAnswer) {
    this.answer = newAnswer;
  }
}
