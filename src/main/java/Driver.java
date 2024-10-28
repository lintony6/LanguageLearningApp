import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * This driver is testing 3 scenarios for the LanguageLearningApp
 * Scenario 1: Tim Tomacka tries to sign up but fails due to choosing
 * a username already taken. He chooses a new username and signs up
 * Scenario 2: Tim begins learning and gets 3/5 questions correct. He
 * then checks his progress.
 * Scenario 3: Tammy logs into her account to view her progress and
 * her problematic phrases. Shes prints a study sheet and logs out.
 * @author Tony Lin
 */
public class Driver {
  private static DictionaryManager dictionaryManager;
  private static LanguageLearningSystemFacade facade;
  private static Scanner keyboard;
  
  /**
   * Initializes the facade and scanner
   */
  public static void startDemo() {
    dictionaryManager = DictionaryManager.getInstance();
    dictionaryManager = DataLoader.loadDictionary();
    facade = LanguageLearningSystemFacade.getInstance();
    keyboard = new Scanner(System.in);
  }

  /**
   * Allows the user to login from the terminal by entering username
   * and password
   */
  public static void login() {
    try {
    System.out.println("Enter your username");
    String userName = keyboard.next();
    keyboard.nextLine();
    System.out.println("Enter your password");
    String password = keyboard.next();
    keyboard.nextLine();
    facade.login(userName, password);
    System.out.println("Welcome back: " + facade.getUser().getFirstName());
    System.out.println("Friends:");
    for(User user : facade.getUser().getFriendList()) {
      System.out.println(user.getUserName());
    }
    } catch(Exception e) {
      System.out.println("Incorrect username/password");
    }
  }

  /**
   * Allows the user to sign up from the terminal by entering 
   * first name, last name, username, password, and email.
   */
  public static void signUp() {
    try {
      System.out.println("Enter your first name");
      String firstName = keyboard.nextLine();
      System.out.println("Enter your last name");
      String lastName = keyboard.nextLine();
      System.out.println("Enter your username");
      String userName = keyboard.nextLine();
      System.out.println("Enter your password (At least 8 characters)");
      String password = keyboard.nextLine();
      System.out.println("Enter your email");
      String email = keyboard.nextLine();
      checkSignUp(firstName, lastName, userName, password, email);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Checks arguments to ensure when the user signs up 
   * all values they entered are available/valid.
   * @param firstName First name of user
   * @param lastName Last name of user
   * @param userName Username of user
   * @param password Password of user
   * @param email Email of user
   */
  private static void checkSignUp(String firstName, String lastName,
  String userName, String password, String email)  {
    try {
      if(password.length() < 8) {
        System.out.println("Please enter a password at least 8 characters");
        return;
      }
      ArrayList<User> allUsers = facade.getAllUsers();
      for(User user : allUsers) {
        if(user.getUserName().equals(userName)) {
          System.out.println("This username is taken, please choose a new one");
          return;
        }
        else if (user.getEmail().equals(email)) {
          System.out.println("This email is already registered, please choose a new one");
          return;
        }
      }
      facade.signUp(firstName, lastName, userName, password, email, UUID.randomUUID());
      System.out.println("Welcome new user: " + facade.getUser().getFirstName());
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  /**
   * Saves all data to json files
   */
  public static void logout() {
    facade.logout();
    System.out.println("Logged Out");
  }

  /**
   * Presents options for user to use the program through terminal
   *  such as login, signup, or logout.
   */
  public static void menuOptions() {
    try {
      System.out.println("Enter 1 to login\nEnter 2 to signup"
      + "\nEnter 9 to logout");
      int menuOptions = keyboard.nextInt();
      keyboard.nextLine();
      switch(menuOptions) {
      case 1: login(); break;
      case 2: signUp(); break;
      case 9: logout(); System.exit(0); break;
      default: System.out.println("Please enter 1, 2, or 9");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Will play the string passed in as argument as a text-to-speech
   * feature
   * @param text to be played out loud
   */
  public static void playSound(String text){
    Narrator.playSound(text);
  }

  public static void playMatching() {
    Narrator.playSound("Matching Game");
    System.out.println("\nMatching Game");
    String prompt = facade.getLesson().matchPrompt();
    System.out.println(prompt);
    ArrayList<Integer> userChoices = new ArrayList<>();
    for(int i = 0; i < 3; ++i) {
      System.out.println("Pick a Spanish word");
      userChoices.add(keyboard.nextInt()-1);
      keyboard.nextLine();
      System.out.println("Pick a English word");
      userChoices.add(keyboard.nextInt()-1);
      keyboard.nextLine();
    }
    System.out.println("Your score on Matching is:");
   System.out.println(facade.getLesson().checkMatching(prompt, userChoices));
  }

  public static void playFlashcards() {
    Narrator.playSound("Flash Cards");
    System.out.println("\nCard 1");
    Narrator.playSound(facade.getLesson().getFlashcards().get(0).getCurrentWord().getForeign());
    System.out.println(facade.getLesson().getFlashcards().get(0).getCurrentWord().getForeign());
    Narrator.playSound(facade.getLesson().getFlashcards().get(0).getCurrentWord().getEnglish());
    System.out.println(facade.getLesson().getFlashcards().get(0).getCurrentWord().getEnglish());
    System.out.println("\nCard 2");
    Narrator.playSound(facade.getLesson().getFlashcards().get(1).getCurrentWord().getForeign());
    System.out.println(facade.getLesson().getFlashcards().get(1).getCurrentWord().getForeign());
    Narrator.playSound(facade.getLesson().getFlashcards().get(1).getCurrentWord().getEnglish());
    System.out.println(facade.getLesson().getFlashcards().get(1).getCurrentWord().getEnglish());
    System.out.println("\nCard 3");
    Narrator.playSound(facade.getLesson().getFlashcards().get(2).getCurrentWord().getForeign());
    System.out.println(facade.getLesson().getFlashcards().get(2).getCurrentWord().getForeign());
    Narrator.playSound(facade.getLesson().getFlashcards().get(2).getCurrentWord().getEnglish());
    System.out.println(facade.getLesson().getFlashcards().get(2).getCurrentWord().getEnglish());
  }

  public static void playMultipleChoice() {
    Narrator.playSound("Multiple Choice Question");
    System.out.println("Multiple Choice Question:");
    System.out.println(facade.getLesson().multipleChoicePrompt());
    System.out.println("Answer Choices");
    System.out.println(facade.getLesson().multipleChoiceAnswers());
    int choice = keyboard.nextInt() - 1;
    System.out.println(facade.getLesson().checkMultipleChoice(choice));
  }

  /**
   * Scenario 1
   */
  public static void scenario1() {
    String firstName = "Jim";
    String lastName = "Smith";
    String userName = "JimSmith01";
    String password = "SmithRocks";
    String email = "JimSmith2000@email.com";
    checkSignUp(firstName, lastName, userName, password, email);
    facade.login(userName,password);
    System.out.println("Jim logs out");
    facade.logout();
    System.out.println("Jim logs back in");
    facade.login(userName, password);
  }

  /**
   * Scenario 2
   */
  public static void scenario2() {
    facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    facade.startLesson(LessonTopic.FAMILY);
    System.out.println("Jim flips through the flashcards");
    playFlashcards();
    System.out.println("Jim now attempts the questions");
    playMultipleChoice();
    playMatching();
    
  }

  /**
   * Scenario 3
   */
  public static void scenario3() {
  }


  public static void main(String[] args) {
    startDemo();
    scenario1();
    scenario2();
 }
}