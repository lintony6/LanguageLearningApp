import java.util.ArrayList;

/**
 * The FillBlank class represents a fill-in-the-blank question.
 * It contains a question and an answer, which is the correct Word.
 * This class allows checking if a user-provided answer is correct.
 * @author Tony Lin and Ishaan Cheema
*/

public class FillBlank implements Question {
  private Word answer;
  private ArrayList<Word> question;

    /**
     * Creates a {@code FillBlank} question.
     * 
     * @param question the question content as a list of words
     * @param answer the correct answer
     */

  public FillBlank(ArrayList<Word> question, Word answer) {
    this.question = question;
    this.answer = answer;
  }

  /*
  * @return the correct answer 
  */

  public ArrayList<Word> getAnswer() {
    ArrayList<Word> word = new ArrayList<>();
    word.add(this.answer);
    return word;
  }

  /*
  * @return the question content 
  */
  
  public ArrayList<Word> getContent() {
    return this.question;
  }

    /**
     * Checks if the given {@code Word} is correct.
     * 
     * @param userAnswer the user's answer
     * @return {@code true} if correct, {@code false} otherwise
     */
  
  public boolean isCorrect(Word userAnswer) {
    return this.answer.equals(userAnswer);
  }

    /**
     * Checks if the given word (in foreign language) is correct.
     * 
     * @param foreignWord the user's answer in foreign language
     * @return {@code true} if correct, {@code false} otherwise
     */
  
  public boolean isCorrect(String foreignWord) {
    return this.answer.getForeign().equals(foreignWord);
  }

    /**
     * Adds a word to the question content.
     * 
     * @param word the word to add
     */

  public void addWordToQuestion(Word word) {
    this.question.add(word);
  }

    /**
     * Removes a word from the question content.
     * 
     * @param word the word to remove
     * @return {@code true} if removed, {@code false} if not found
     */

  public boolean removeWordFromQuestion(Word word) {
    return this.question.remove(word);
  }

    /**
     * Sets a new correct answer.
     * 
     * @param newAnswer the new answer
     */

  public void setAnswer(Word newAnswer) {
    this.answer = newAnswer;
  }

    public boolean isCorrect(ArrayList<Word> answer) {
     return answer.get(0)==this.answer;
    }


    public void setPrompt(ArrayList<Word> prompt) {
     this.question = prompt;
    }


}
