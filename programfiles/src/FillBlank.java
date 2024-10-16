import java.util.ArrayList;

public class FillBlank {
  private Word answer;
  private ArrayList<Word> question;

  // Constructor to initialize the question and answer
  public FillBlank(ArrayList<Word> question, Word answer) {
    this.question = question;
    this.answer = answer;
  }

  // Getter for the correct answer
  public Word getAnswer() {
    return this.answer;
  }

  // Getter for the content of the question
  public ArrayList<Word> getContent() {
    return this.question;
  }

  // Method to check if a given Word matches the correct answer
  public boolean isCorrect(Word userAnswer) {
    return this.answer.equals(userAnswer);
  }

  // Method to check if a given string (e.g., user input) matches the correct answer
  public boolean isCorrect(String foreignWord) {
    return this.answer.getForeign().equals(foreignWord);
  }

  // Method to add a Word to the question (optional if you want to modify the question content)
  public void addWordToQuestion(Word word) {
    this.question.add(word);
  }

  // Method to remove a Word from the question (optional if you want to modify the question content)
  public boolean removeWordFromQuestion(Word word) {
    return this.question.remove(word);
  }

  // Method to replace the current answer
  public void setAnswer(Word newAnswer) {
    this.answer = newAnswer;
  }
}
