import java.util.ArrayList;
import java.util.HashMap;

public class Progress{
    private HashMap<Lesson, Integer> LessonProgress;
    private ArrayList<Question> incorrectQuestions;
/** 
 * Constructor initalizes the lesson progress tracker;
 */
    public Progress(){
        this.LessonProgress = new HashMap<>();
        this.incorrectQuestions = new ArrayList<>();

    }

    public ArrayList<Question> getIncorrect(Lesson lesson){
        return incorrectQuestions;


    }
    /**
     * Adds or updates progress for a lesson
     * 
     * @param lesson the lesson to update the progress for
     * @param progress the value to set
     */
    public void updateProgress(Lesson lesson, Integer progress){
        lessonProgress.put(lesson, progress);

    }
    public Integer getLessonProgress(Lesson lesson){
        return lessonProgress.getOrDefault(lesson, null);
    }
}