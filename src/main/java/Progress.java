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
  private HashMap<LessonTopic, ArrayList<Object>> incomplete;
  private HashMap<LessonTopic, HashMap<Question,Integer>> trouble;
  private int languageProgress;

/** 
 * Constructor initalizes the lesson progress tracker;
 */
  public Progress(){
    this.lessonProgress = new HashMap<>();
    this.incomplete = new HashMap<>();
    this.trouble = new HashMap<>();
    for(LessonTopic topic : LessonTopic.values()) {
      this.lessonProgress.put(topic, 0);
      this.trouble.put(topic, new HashMap<>());
    }
    languageProgress = 0;
   }

   /**
    * Adds a ArrayList<Question> to the stored collection of incomplete
    * questions
    * @param topic of the lesson
    * @param questions ArrayList<Question> of the incomplete questions
    */
  public void setIncomplete(LessonTopic topic, ArrayList<Object> questions) {
    this.incomplete.put(topic, questions);
    this.lessonProgress.put(topic, 9 - questions.size());
  }

  /**
   * Returns a ArrayList<Question> containing all questions of a 
   * lesson that the user has not completed
   * @param topic of the lesson
   * @return ArrayList<Question> of the incompleted questions
   */
  public ArrayList<Object> getIncomplete(LessonTopic topic){
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
    this.trouble.get(topic).remove(question);
    try {
      
    } catch (Exception e) {
    }
  }

  public void updateComplete(LessonTopic topic) {
    this.languageProgress++;
    this.lessonProgress.put(topic, this.lessonProgress.get(topic) + 1);
  }

  /**
   * Adds a question to the incomplete questions HashMap
   * @param topic LessonTopic of the incomplete question
   * @param question that is incomplete
   */
  public void updateIncomplete(LessonTopic topic, Question toAdd) {
    for(Question question : this.trouble.get(topic).keySet()) {
      System.out.println("hi");
        if(!this.trouble.get(topic).containsKey(question)) {
          this.trouble.get(topic).put(question, 1);
        }
        else
          this.trouble.get(topic).put(question, this.trouble.get(topic).get(question) + 1);
    }
    this.incomplete.get(topic).add(toAdd);
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
   * Removes the trouble question from the collection
   * @param question to be removed
   */
  public void removeTrouble(LessonTopic topic, Question question) {
    this.trouble.get(topic).remove(question);
  }

  public void setTrouble(HashMap<LessonTopic, HashMap<Question, Integer>>map) {
    this.trouble = map;
  }

  public HashMap<LessonTopic,HashMap<Question,Integer>> getTrouble() {
    return this.trouble;
  }
}