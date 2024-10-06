import java.util.ArrayList;

/**
 * This class named FillBlank contains a Word  for the answer and an
 * ArrayList of Word objects for the question.
 * @author Tony Lin
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


}
