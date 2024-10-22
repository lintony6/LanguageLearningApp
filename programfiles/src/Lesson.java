import java.util.ArrayList;

/**
 * The Lesson class contains a topic, questions, flashcards,
 *  and a picture story.
 * @author Tony Lin and Ishaan Cheema
 */

public class Lesson {
  private LessonTopic topic;
  private ArrayList<Question> questions;
  private ArrayList<Flashcard> flashcards;
  private PictureStory story;
  private boolean isCompleted;

  public Lesson(LessonTopic topic, ArrayList<Question> questions, ArrayList<Flashcard> flashcards, PictureStory story) {
    this.topic = topic;
    this.questions = questions != null ? questions : new ArrayList<>();
    this.flashcards = flashcards != null ? flashcards : new ArrayList<>();
    this.story = story;
    this.isCompleted = false;
  }

  public Lesson(LessonTopic topic) {
    this(topic, null, null, null);
  }

  public void startLesson() {
    System.out.println("Starting lesson on topic: " + topic.getName());
  }

  public void completeLesson() {
    this.isCompleted = true;
    System.out.println("Lesson on topic '" + topic.getName() + "' completed.");
  }

  public void addQuestion(Question question) {
    questions.add(question);
  }

  public void addFlashcard(Flashcard flashcard) {
    flashcards.add(flashcard);
  }

  public void setStory(PictureStory story) {
    this.story = story;
  }

  public LessonTopic getTopic() {
    return this.topic;
  }

  public ArrayList<Question> getQuestions() {
    return this.questions;
  }

  public ArrayList<Flashcard> getFlashcards() {
    return this.flashcards;
  }
  
  public PictureStory getStory() {
    return this.story;
  }

  public boolean isCompleted() {
    return this.isCompleted;
  }
}
