import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Progress class tracks a the user's progress through a language, 
 * progress through each lesson in the langauge, all of their 
 * incomplete questions, as well as their trouble questions.
 * @author Tony Lin and Brian Lee
 */
public class Progress{
  private HashMap<LessonTopic, Integer> lessonProgress;
  private HashMap<LessonTopic, ArrayList<Question>> incomplete;
  private ArrayList<Question> trouble;
  private int languageProgress;

/** 
 * Constructor initalizes the lesson progress tracker;
 */
  public Progress(){
    this.lessonProgress = new HashMap<>();
    this.incomplete = new HashMap<>();
    languageProgress = 0;
   }

   /**
    * Adds a ArrayList<Question> to the stored collection of incomplete
    * questions
    * @param topic of the lesson
    * @param questions ArrayList<Question> of the incomplete questions
    */
  public void setIncomplete(LessonTopic topic, ArrayList<Question> questions) {
    this.incomplete.put(topic, questions);
    this.lessonProgress.put(topic, 5 - questions.size());
  }

  /**
   * Returns a ArrayList<Question> containing all questions of a 
   * lesson that the user has not completed
   * @param topic of the lesson
   * @return ArrayList<Question> of the incompleted questions
   */
  public ArrayList<Question> getIncomplete(LessonTopic topic){
    return this.incomplete.get(topic);
    }

    /**
     * Updates the user's language progress and lesson progress 
     * while also removing the completed question from the incomplete
     * questions collection
     */
  public void updateCorrect(LessonTopic topic, Question question) {
    this.languageProgress++;
    this.lessonProgress.put(topic, this.lessonProgress.get(topic) + 1);
    this.incomplete.get(topic).remove(question);
    try {
      this.trouble.remove(question);
    } catch (Exception e) {
    }
  }

  /**
   * Adds a question to the incomplete questions HashMap
   * @param topic LessonTopic of the incomplete question
   * @param question that is incomplete
   */
  public void updateIncomplete(LessonTopic topic, Question question) {
    this.incomplete.get(topic).add(question);
  }

  /**
   * Returns the overall progress the user has made through an entire
   * language
   * @return int representing how many total questions the user has 
   * completed in a language
   */
  public int getLanguageProgress() {
    return this.languageProgress;
}

/**
 * Returns the lesson progress for a given lessontopic
 * @param topic of the lesson
 * @return int representing how many questions the user has completed
 * in that lesson
 */
  public int getLessonProgress(LessonTopic topic) {
    return this.lessonProgress.get(topic);
  }

  /**
   * Adds a trouble question to the collection
   * @param question to be added
   */
  public void addTrouble(Question question) {
    this.trouble.add(question);
  }

  /**
   * Removes the trouble question from the collection
   * @param question to be removed
   */
  public void removeTrouble(Question question) {
    this.trouble.remove(question);
  }
}