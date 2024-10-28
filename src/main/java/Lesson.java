import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.HashMap;

/**
 * The Lesson class contains a topic, questions, flashcards,
 *  and a picture story.
 * @author Tony Lin and Ishaan Cheema
 */

public class Lesson {
  private LessonTopic topic;
  private MultipleChoice multipleChoice;
  private Matching matching;
  private FillBlank fillBlank;
  private ArrayList<Flashcard> flashcards;
  private PictureStory story;
  private boolean isCompleted;
  private LanguageDifficulty difficulty;
  private static DictionaryManager manager;

  public Lesson(LanguageDifficulty difficulty, LessonTopic topic) {
    manager = DictionaryManager.getInstance();
    this.difficulty = difficulty;
    this.topic = topic;
    this.flashcards = new ArrayList<Flashcard>();
    createFlashCard();
    createMatching();
    createMultipleChoice();
    //this.story = new PictureStory();
  }

  private void createFlashCard() {
    ArrayList<Word> words = manager.getWordsByTopic(this.difficulty, this.topic);
    for(Word word : words) {
      Flashcard card = new Flashcard(word);
      this.flashcards.add(card);
    }

  }

  public String multipleChoiceAnswers() {
    StringBuilder toReturn = new StringBuilder();
    ArrayList<Word> answers = this.multipleChoice.getAnswers();
    for(int i = 0; i < 4; ++i){
      toReturn.append(answers.get(i).getForeign());
      toReturn.append("\n");
    }
    return toReturn.toString();
  }

  public String multipleChoicePrompt() {
    StringBuilder prompt = new StringBuilder();
    for(Word word : multipleChoice.getContent()) {
      prompt.append(word.getEnglish());
      prompt.append(" ");
    }
    return prompt.toString();
  }

  private void createMultipleChoice() {
    Random random = new Random();
    ArrayList<Word> allWords = new ArrayList<Word>();
    ArrayList<Word> topicWords = manager.getWordsByTopic(this.difficulty, this.topic);
    for(LessonTopic testtopic : LessonTopic.values()) {
      allWords.addAll(manager.getWordsByTopic(this.difficulty, testtopic));
    }
    Word answerWord = topicWords.get(random.nextInt(3));
    String multipleChoiceQuestion = manager.getMeaning(difficulty, topic, answerWord.getForeign());
    String[] multipleChoiceSplit = multipleChoiceQuestion.split(" ");
    ArrayList<Word> multipleChoicePrompt = new ArrayList<Word>();
    for(String word : multipleChoiceSplit)
      multipleChoicePrompt.add(new Word("", word));
    ArrayList<Word> answers = new ArrayList<Word>();
    int correct = random.nextInt(4);
    for(int i = 0; i < correct; ++i) {
      Word word = allWords.get(random.nextInt(allWords.size()));
      answers.add(word);
    }
    answers.add(answerWord);
    for(int i = 0; i < 3 - correct; ++i) {
      Word word = allWords.get(random.nextInt(allWords.size()));
      answers.add(word);
    }
    this.multipleChoice = new MultipleChoice(multipleChoicePrompt, answers, correct);
  }

  private void createFillBlank() {

  }

  public String matchPrompt() {
    int i = 1;
    StringBuilder prompt = new StringBuilder();
    ArrayList<Word> words = this.matching.getContent();
    for(Word word : words) {
      prompt.append(word.getForeign());
      prompt.append("\n");
      ++i;
    }
    Collections.shuffle(words);
    for(Word word : words) {
      prompt.append(word.getEnglish());
      prompt.append("\n");
      ++i;
    }
    return prompt.toString();
  }

  public int checkMatching(String prompt, ArrayList<Integer> answers) {
    String[] promptsplit = prompt.split("\n");
    HashMap<String, String> pairs = new HashMap<String,String>();
    int correct = 0;
    for(int i = 0; i < 5; i = i + 2) {
      boolean isCorrect = this.matching.isCorrect(promptsplit[answers.get(i)],promptsplit[answers.get(i+1)]);
      if (isCorrect)
        ++correct;
    }
    return correct;
  }

  private void createMatching() {
    ArrayList<Word> matching = manager.getWordsByTopic(this.difficulty, this.topic);
    this.matching = new Matching(matching);
  }
  
  public void startLesson() {
    System.out.println("Starting lesson on topic: " + topic);
  }

  public void completeLesson() {
    this.isCompleted = true;
    System.out.println("Lesson on topic '" + topic + "' completed.");
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

  public MultipleChoice getMultipleChoice() {
    return this.multipleChoice;
  }

  public ArrayList<Flashcard> getFlashcards() {
    return this.flashcards;
  }

  public Matching getMatching() {
    return this.matching;
  }
  
  public PictureStory getStory() {
    return this.story;
  }

  public boolean isCompleted() {
    return this.isCompleted;
  }
}
