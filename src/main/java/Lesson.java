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
  private ArrayList<MultipleChoice> multipleChoice;
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
    this.multipleChoice = new ArrayList<MultipleChoice>();
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

  public boolean checkMultipleChoice(MultipleChoice question, int answer) {
    if(this.multipleChoice.contains(question))
    for(MultipleChoice choice : this.multipleChoice)
      if(choice.equals(question)){
        ArrayList<Word> correctanswer = choice.getAnswer();
        return question.getAnswers().get(answer).equals(correctanswer.get(0));
      }
    return false;
  }

  public String multipleChoiceAnswers(MultipleChoice question) {
    StringBuilder toReturn = new StringBuilder();
    for(MultipleChoice choice : this.multipleChoice)
      if(choice.equals(question)){
        ArrayList<Word> answers = question.getAnswers();
        for(int i = 0; i < 4; ++i){
          toReturn.append(answers.get(i).getForeign());
          toReturn.append("\n");
        }
        return toReturn.toString();
    }
    return null;
  }

  public String multipleChoicePrompt(MultipleChoice question) {
    StringBuilder prompt = new StringBuilder();
    for(MultipleChoice choice : this.multipleChoice)
      if(choice.equals(question)){
        for(Word word : choice.getContent()) {
        prompt.append(word.getEnglish());
        prompt.append(" ");
    }
    return prompt.toString();
      }
    return null;
  }

  private void createMultipleChoice() {
    Random random = new Random();
    ArrayList<Word> allWords = new ArrayList<Word>();
    ArrayList<Word> topicWords = manager.getWordsByTopic(this.difficulty, this.topic);
    for(LessonTopic testtopic : LessonTopic.values()) {
      if(testtopic.equals(this.topic))
        continue;
      allWords.addAll(manager.getWordsByTopic(this.difficulty, testtopic));
    }
    for(int i = 0; i < 3; ++i) {
      Word answerWord = topicWords.get(i);
      String multipleChoiceQuestion = manager.getMeaning(difficulty, topic, answerWord.getForeign());
      String[] multipleChoiceSplit = multipleChoiceQuestion.split(" ");
      ArrayList<Word> multipleChoicePrompt = new ArrayList<Word>();
      for(String word : multipleChoiceSplit)
        multipleChoicePrompt.add(new Word("", word));
        ArrayList<Word> answers = new ArrayList<Word>();
        int correct = random.nextInt(4);
      for(int j = 0; j < correct; ++j) {
        Word word = allWords.get(random.nextInt(allWords.size()));
        answers.add(word);
    }
      answers.add(answerWord);
      for(int j = 0; j < 3 - correct; ++j) {
      Word word = allWords.get(random.nextInt(allWords.size()));
      answers.add(word);
    }
      this.multipleChoice.add(new MultipleChoice(multipleChoicePrompt, answers, correct));
  }
  }

  private void createFillBlank() {
  
  }

  public String matchPrompt() {
    StringBuilder prompt = new StringBuilder();
    ArrayList<Word> words = this.matching.getContent();
    for(Word word : words) {
      prompt.append(word.getForeign());
      prompt.append("\n");
    }
    Collections.shuffle(words);
    for(Word word : words) {
      prompt.append(word.getEnglish());
      prompt.append("\n");
    }
    return prompt.toString();
  }

  public int checkMatching(String prompt, ArrayList<Integer> answers) {
    String[] promptsplit = prompt.split("\n");
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

  public MultipleChoice getMultipleChoice(int num) {
    return this.multipleChoice.get(num);
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

  public void setFillBlank(String prompt, Word answer) {
    this.fillBlank = new FillBlank(prompt, answer);
  }

  public FillBlank getFillBlank() {
    return this.fillBlank;
  }

}
