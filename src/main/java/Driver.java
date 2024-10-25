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
    System.out.println("Welcome " + facade.getUser().getFirstName());
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
      int error = checkSignUp(userName, password, email);
      switch(error) {
      case 0:facade.signUp(firstName, lastName, userName, password, email, UUID.randomUUID());
             System.out.println("Welcome " + facade.getUser().getFirstName());
             break;
      case 1: System.out.println("This username is taken, please choose a new one"); action(); break;
      case 2: System.out.println("Please enter a password at least 8 characters"); action(); break;
      case 3: System.out.println("This email is already registered, please choose a new one"); action(); break;
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }
  private static int checkSignUp(String userName, String password, String email)  {
    try {
      if(password.length() < 8)
        return 2;
      ArrayList<User> allUsers = facade.getAllUsers();
      for(User user : allUsers) {
        if(user.getUserName().equals(userName))
          return 1;
        else if (user.getEmail().equals(email))
          return 3;
      }
      return 0;
    } catch (Exception e) {
      System.out.println(e);
      return 0;
    }
  }
  public static void logout() {
    facade.logout();
  }

  public static void action() {
    try {
      System.out.println("Enter 1 to login\nEnter 2 to signup"
      + "\nEnter 9 to logout");
      int action = keyboard.nextInt();
      keyboard.nextLine();
      switch(action) {
      case 1: login(); break;
      case 2: signUp(); break;
      case 9: logout(); System.exit(0); break;
      default: System.out.println("Please enter 1, 2, or 9");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    Narrator.playSound("Welcome");
    startDemo();
    action();
 }
}