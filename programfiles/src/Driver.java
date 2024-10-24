import java.util.Scanner;
import java.util.UUID;
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
      if(password.length() <8) {
        System.out.println("Please enter a valid password");
        signUp();
        return;
      }
      facade.signUp(firstName, lastName, userName, password, UUID.randomUUID());
      System.out.println("Welcome " + facade.getUser().getFirstName());
    } catch (Exception e) {
      System.out.println(e);
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
    startDemo();
    action();
    action();
 }
}