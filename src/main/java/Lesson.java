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
  private ArrayList<Object> questions;
  private PictureStory story;
  private LanguageDifficulty difficulty;
  private static DictionaryManager manager;

  public Lesson(LanguageDifficulty difficulty, LessonTopic topic) {
    manager = DictionaryManager.getInstance();
    this.difficulty = difficulty;
    this.topic = topic;
    this.questions = new ArrayList<>();
    createFlashCard();
    createMatching();
    createMultipleChoice();
    createFillBlank();
    //this.story = new PictureStory();
  }

  private void createFlashCard() {
    ArrayList<Word> words = manager.getWordsByTopic(this.difficulty, this.topic);
    for(Word word : words) {
      Flashcard card = new Flashcard(word);
      this.questions.add(card);
    }

  }

  public boolean checkMultipleChoice(MultipleChoice multipleChoice, int answer) {
    if(this.questions.contains(multipleChoice))
    for(Object question : this.questions)
      if(question.equals(multipleChoice)){
        MultipleChoice choice = (MultipleChoice)question;
        ArrayList<Word> correctanswer = choice.getAnswer();
        return multipleChoice.getAnswers().get(answer).equals(correctanswer.get(0));
      }
    return false;
  }

  public String multipleChoiceAnswers(MultipleChoice multipleChoice) {
    StringBuilder toReturn = new StringBuilder();
    for(Object question : this.questions)
      if(question.equals(multipleChoice)){
        MultipleChoice choice = (MultipleChoice)question;
        ArrayList<Word> answers = choice.getAnswers();
        for(int i = 0; i < 4; ++i){
          toReturn.append(answers.get(i).getForeign());
          toReturn.append("\n");
        }
        return toReturn.toString();
    }
    return null;
  }

  public String multipleChoicePrompt(MultipleChoice multipleChoice) {
    StringBuilder prompt = new StringBuilder();
    for(Object object : this.questions)
      if(object.equals(multipleChoice)){
        MultipleChoice choice = (MultipleChoice)object;
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
      this.questions.add(new MultipleChoice(multipleChoicePrompt, answers, correct,i));
  }
  }

  private void createFillBlank() {
    switch(this.topic) {
      case SCHOOL:
      this.questions.add(new FillBlank("Every night before bed I like to read a ___.", manager.getWord(this.difficulty,this.topic,"libro"),0));
      this.questions.add(new FillBlank("I hate sitting in the ___, it's so uncomfortable in school.", manager.getWord(this.difficulty,this.topic,"mesa"),1));
      this.questions.add(new FillBlank("All the students in the ___ were talking so loudly.", manager.getWord(this.difficulty,this.topic,"clase"),2));
      break;
      case WEATHER:
      this.questions.add(new FillBlank("During the day, the ___ shines brightly", manager.getWord(this.difficulty,this.topic,"sol"),0));
      this.questions.add(new FillBlank("The sky was filled with fluffy ___", manager.getWord(this.difficulty,this.topic,"nube"),1));
      this.questions.add(new FillBlank("The thirsty plants were nourished when it started to ___", manager.getWord(this.difficulty,this.topic,"lluvia"),2));
      break;
      case FAMILY:
      this.questions.add(new FillBlank("The woman who gave birth to me is my ___", manager.getWord(this.difficulty,this.topic,"madre"),0));
      this.questions.add(new FillBlank("The man who gave birth to me is my ___", manager.getWord(this.difficulty,this.topic,"padre"),1));
      this.questions.add(new FillBlank("My parents had two sons, their relationship is ___", manager.getWord(this.difficulty,this.topic,"hermano"),2));
      break;
      case FOOD:
      this.questions.add(new FillBlank("____ is from cows", manager.getWord(this.difficulty,this.topic,"leche"),0));
      this.questions.add(new FillBlank("____ is a red fruit that grows in trees", manager.getWord(this.difficulty,this.topic,"manzana"),1));
      this.questions.add(new FillBlank("The main ingredient in a sandwich is ___", manager.getWord(this.difficulty,this.topic,"pan"),2));
      break;
      case PETS:
      this.questions.add(new FillBlank("A ___ enjoys climbing high places and napping", manager.getWord(this.difficulty,this.topic,"gato"),0));
      this.questions.add(new FillBlank("The ___ loves to play fetch", manager.getWord(this.difficulty,this.topic,"perro"),1));
      this.questions.add(new FillBlank("My pet ____ always flies to greet me when I come home", manager.getWord(this.difficulty,this.topic,"pájaro"),2));
      break;
    }
  }

  public String matchPrompt() {
    StringBuilder prompt = new StringBuilder();
    for(Object question : this.questions) {
      if(question instanceof Matching) {
        Matching match = (Matching) question;
        ArrayList<Word> words = match.getContent();
        for(Word word : words) {
          prompt.append(word.getForeign());
          prompt.append("\n");
        }
        Collections.shuffle(words);
        for(Word word : words) {
          prompt.append(word.getEnglish());
          prompt.append("\n");
        }
      }
    }
    return prompt.toString();
  }

  public int checkMatching(String prompt, ArrayList<Integer> answers) {
    String[] promptsplit = prompt.split("\n");
    int correct = 0;
    for(Object question : this.questions) {
      if(question instanceof Matching) {
        Matching match = (Matching) question;
        for(int i = 0; i < 5; i = i + 2) {
          boolean isCorrect = match.isCorrect(promptsplit[answers.get(i)],promptsplit[answers.get(i+1)]);
          if (isCorrect)
            ++correct;
        }
      }
    }
    return correct;
  }

  private void createMatching() {
    ArrayList<Word> matching = manager.getWordsByTopic(this.difficulty, this.topic);
    this.questions.add(new Matching(matching));
  }
  
  public void startLesson() {
    System.out.println("Starting lesson on topic: " + topic);
  }


  public void addFlashcard(Flashcard flashcard) {
    this.questions.add(flashcard);
  }

  public void setStory(PictureStory story) {
    this.story = story;
  }

  public LessonTopic getTopic() {
    return this.topic;
  }

  public MultipleChoice getMultipleChoice(int num) {
    int i = 0;
    for(Object question : this.questions)
      if(question instanceof MultipleChoice) {
        MultipleChoice toReturn = (MultipleChoice) question;
        if(num == toReturn.getId())
          return (MultipleChoice) question;
        ++i;
      }
    return null;
  }

  public ArrayList<Flashcard> getFlashcards() {
    ArrayList<Flashcard> cards = new ArrayList<>();
    for(Object object : this.questions)
      if(object instanceof Flashcard) {
        Flashcard card = (Flashcard)object;
        cards.add(card);
      }
    return cards;
  }

  public Matching getMatching() {
    for(Object question : this.questions)
      if(question instanceof Matching) 
        return (Matching)question;
    return null;
  }
  
  public PictureStory getStory() {
    return this.story;
  }

  public FillBlank getFillBlank(int num) {
    int i = 0;
    for(Object question : this.questions)
      if(question instanceof FillBlank) {
        FillBlank toReturn = (FillBlank)question;
        if(num == toReturn.getId() ) {
          return (FillBlank) question;
        }
        ++i;
      }
    return null;
  }

  public boolean checkFillBlank(int num, String input) {
    int i = 0;
    for(Object question : this.questions)
      if(question instanceof FillBlank) {
        if(i == num ) {
          FillBlank toCheck = (FillBlank)question;
          return input.equalsIgnoreCase(toCheck.getAnswer().get(0).getForeign());
        }
        ++i;
      }
    return false;
  }

  public ArrayList<Object> getQuestions() {
    return this.questions;
  }
}
