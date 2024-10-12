import java.util.ArrayList;
public class Driver {
  public static void main(String[] args) {
      try {
        String filePath = "CSCE247/LanguageLearningApp/json/user.json";   //enter your file path here
        UserList userList = DataLoader.loadUsers(filePath);
        User user = userList.login("john_doe123","extremelysecure");
        ArrayList<User> userFriends = user.getFriendList();
        System.out.println(user.getFirstName() + " " +user.getUserID()
          + "\n" +user.getSettings() + "\nFriends");
        for(int i = 0; i < userFriends.size(); ++i) {
          System.out.println(userFriends.get(i).getUserName());
        }
    } catch (Exception e) {
        System.out.println(e);
  }
 }
}