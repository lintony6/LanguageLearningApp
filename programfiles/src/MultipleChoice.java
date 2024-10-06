import java.util.ArrayList;

/**
 * The class `MultipleChoice` represents a multiple choice question
 * with a list of words for thequestion, an array of lists of words
 * for the answer choices, and an integer to track the index of
 * the correct answer.
 * @author Tony Lin 
 */
public class MultipleChoice {
  private ArrayList<Word> question;
  private ArrayList<Word>[] answers;
  private int correct;

  public MultipleChoice(ArrayList<Word> question,
                        ArrayList<Word>[] answers, int correct) {
    this.question = question;
    this.answers = answers;
    this.correct = correct;
  }

  public ArrayList<Word> getContent() {
    return this.question;
  }

  public ArrayList<Word>[] getAnswers() [
    return this.answers;
}

  public boolean isCorrect(int userAnswer) {
    return this.correct == userAnswer;
}

  public ArrayList<Word> getCorrect() {
    return answers[correct];
  }

}
