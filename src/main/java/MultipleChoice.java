import java.util.ArrayList;

/**
 * The class `MultipleChoice` represents a multiple choice question
 * with a list of words for the question, an array of lists of words
 * for the answer choices, and an integer to track the index of
 * the correct answer.
 * @author Tony Lin and Ishaan Cheema
 */
public class MultipleChoice {
  private ArrayList<Word> question;
  private ArrayList<Word>[] answers;
  private int correct;

    /**
     * Creates a {@code MultipleChoice} question.
     * 
     * @param question the question words
     * @param answers the answer choices
     * @param correct index of the correct answer
     */

  public MultipleChoice(ArrayList<Word> question,
                        ArrayList<Word>[] answers, int correct) {
    this.question = question;
    this.answers = answers;
    this.correct = correct;
  }

  /*
  * @return the question content 
  */

  public ArrayList<Word> getContent() {
    return this.question;
  }

  /*
  * @return the answer choices 
  */

  public ArrayList<Word>[] getAnswers() {
    return this.answers;
  }

  /*
  * Checks if the user-selected answer is correct.
  */

  public boolean isCorrect(int userAnswer) {
    return this.correct == userAnswer;
  }

  /*
  * @return the correct answer choice 
  */

  public ArrayList<Word> getCorrect() {
    return answers[correct];
  }

}
