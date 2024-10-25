import java.util.ArrayList;
import java.util.HashMap;

public class Progress{
  private HashMap<Lesson, Integer> lessonProgress;
  private ArrayList<Lesson> lessons;
/** 
 * Constructor initalizes the lesson progress tracker;
 */
  public Progress(){
    this.lessonProgress = new HashMap<>();
   }

  public void setLessons(ArrayList<Lesson> lessons) {
    this.lessons = lessons;
    for(Lesson lesson : this.lessons) {
      this.lessonProgress.put(lesson, 0);
    }
  }
  public ArrayList<Question> getIncorrect(Question question){
    return incorrectQuestions;
    }

public void updateProgress( LessonTopic topic, Question question){
  for(Lesson lesson : lessons) {
    if(lesson.getTopic().equals(topic)) {
      if(question instanceof PictureStory) {
        lessonProgress.put(lesson, null)
      }
        
    }
    }
    }
  public Integer getLessonProgress(Lesson lesson){
    return lessonProgress.getOrDefault(lesson, null);
    }
}