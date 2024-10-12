import java.util.ArrayList;
public class Driver {
  public static void main(String[] args) {
      try {
        String filePath = "";   //enter your file path here
        ArrayList<User> userList = DataLoader.loadUsers(filePath);
        for(User user : userList) {
          System.out.println(user.getFirstName() + " " +user.getUserID());
        }
    } catch (Exception e) {
        System.out.println("File not found");
  }
 }
}