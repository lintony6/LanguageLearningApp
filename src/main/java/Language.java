import java.util.ArrayList;

/**
 * The class Language contains lessons, the difficulty,
 * and progress.
 * @author Tony Lin
 */
public class Language {
  private ArrayList<Lesson> lessons;
  private LanguageDifficulty difficulty;
  private Progress progress;
  private ForeignLanguage foreignLanguage;

  /**
   * Parameterized constructor that takes a foreign language and 
   * difficulty and creates instance of language containing all of the 
   * lessons at that difficulty
   * @param difficulty of the Language
   * @param language What language should the instance be of (Spanish)
   */
  public Language(ForeignLanguage language, LanguageDifficulty difficulty) {
    this.lessons = new ArrayList<Lesson>();
    this.difficulty = difficulty;
    this.progress = new Progress();
    this.foreignLanguage = language;
  }
  
  /** Sets this languages difficulty
   * @param difficulty to be set for this language
   */
  public void setDifficulty(LanguageDifficulty difficulty) {
    this.difficulty = difficulty;
  }
  
  public LanguageDifficulty getDifficulty() {
    return this.difficulty;
  }

  /** Returns the 3 difficulty options in nice formatted view
   * @return String of all 3 difficulty options
   */
  public String getDifficultyOptions() {
    return LanguageDifficulty.EASY + "\n" + LanguageDifficulty.MEDIUM
    + "\n" + LanguageDifficulty.HARD;
  }

  /** Returns what language this language class currently is
   * ie (Spanish)
   * @return ForeignLanguage the type of language fo this class
   */
  public ForeignLanguage getForeignLanguage() {
    return this.foreignLanguage;
  }
  
  /** Will return a specific lesson based on which topic 
   * the user wants to complete
   * @param topic of the lesson requested
   * @return Lesson for the user to complete
   */
  public Lesson getLesson(LessonTopic topic) {
    for(Lesson lesson : lessons) {
      if(lesson.getTopic().equals(topic))
        return lesson;
    }
  }
  
  /** Completes the lesson and updates the languages progress
   * @param topic of the lesson that was completed
   */
  public void completeLesson(LessonTopic topic) {
    for(Lesson lesson : lessons) {
      if(lesson.getTopic().equals(topic)) {
        lesson.completeLesson();
        //progress.updateProgress(lesson);
      }
    }
  }

  public int getLanguageProgress() {
    return progress.getLanguageProgress();
  }
}