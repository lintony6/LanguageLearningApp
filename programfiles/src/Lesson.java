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

  // Constructor to initialize the Lesson with a topic, and optionally the questions, flashcards, and story
  public Lesson(LessonTopic topic, ArrayList<Question> questions, ArrayList<Flashcard> flashcards, PictureStory story) {
    this.topic = topic;
    this.questions = questions != null ? questions : new ArrayList<>();
    this.flashcards = flashcards != null ? flashcards : new ArrayList<>();
    this.story = story;
    this.isCompleted = false;
  }

  // Constructor if you want to create a lesson with only a topic at first
  public Lesson(LessonTopic topic) {
    this(topic, null, null, null);
  }

  // Method to start the lesson, might include setting some initial states, showing content, etc.
  public void startLesson() {
    System.out.println("Starting lesson on topic: " + topic.getName());
    // You could add additional code to load questions, flashcards, or the story here
  }

  // Method to complete the lesson, marking it as completed
  public void completeLesson() {
    this.isCompleted = true;
    System.out.println("Lesson on topic '" + topic.getName() + "' completed.");
  }

  // Method to add a question to the lesson
  public void addQuestion(Question question) {
    questions.add(question);
  }

  // Method to add a flashcard to the lesson
  public void addFlashcard(Flashcard flashcard) {
    flashcards.add(flashcard);
  }

  // Method to set a picture story for the lesson
  public void setStory(PictureStory story) {
    this.story = story;
  }

  // Getter for topic
  public LessonTopic getTopic() {
    return this.topic;
  }

  // Getter for questions
  public ArrayList<Question> getQuestions() {
    return this.questions;
  }

  // Getter for flashcards
  public ArrayList<Flashcard> getFlashcards() {
    return this.flashcards;
  }

  // Getter for picture story
  public PictureStory getStory() {
    return this.story;
  }

  // Method to check if the lesson is completed
  public boolean isCompleted() {
    return this.isCompleted;
  }
}