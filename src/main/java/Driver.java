import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;


public class Driver {
  private static LanguageLearningSystemFacade facade;
  private static Scanner keyboard;
  
  public static void startDemo() {
    facade = LanguageLearningSystemFacade.getInstance();
    keyboard = new Scanner(System.in);
  }

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
  public static void logout() {
    facade.logout();
    System.out.println("Logged Out");
  }

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

  public static void playSound(String text){
    Narrator.playSound(text);
  }

  public static void scenario1() {
    String firstName = "Tim";
    String lastName = "Tomacka";
    String userName = "ttomacka";
    String password = "timtomacka";
    String email = "timtomacka@email.com";
    checkSignUp(firstName, lastName, userName, password, email);
    userName = "ttom";
    checkSignUp(firstName, lastName, userName, password, email);
    facade.logout();
    facade.login(userName,password);
    System.out.println("Welcome back: " + facade.getUser().getFirstName());
  }

  public static void main(String[] args) {
    startDemo();
    playSound("Hola mi nombre es tony");
    scenario1();
    facade.logout();
 }
}