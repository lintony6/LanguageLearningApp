import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

/**
 * The class UserList maintains a collection of Users using a 
 * HashMap with UUID as the key and the User as the value;
 * @author Tony Lin
 */
public class UserList {
  private HashMap<UUID, User> users;
  private static UserList userList;

  private UserList() {
    users = new HashMap<UUID, User>();
  }

  public static UserList getInstance() {
    if(userList == null)
      userList = new UserList();
    return userList;
  }

  public User addUser(String firstName, String lastName,
                      String userName, String password) {
    User toAdd = new User(firstName, lastName, userName, password);
    users.put(toAdd.getUserID(), toAdd);
    return toAdd;
    }

  public User getUser(UUID userID) {
    return users.get(userID);
  }

  public User editUser(UUID userID, int change, String updated) {
    switch(change) {
      case 0: users.get(userID).setFirstName(updated); break;
      case 1: users.get(userID).setLastName(updated); break;
      case 2: users.get(userID).setUserName(updated); break;
      case 3: users.get(userID).setPassword(updated); break;
    }
    return users.get(userID);
  }

  public boolean removeUser(UUID userID) {
    try {
      users.remove(userID);
      return true;
    } catch(Exception e) {
      return false;
    }
  }

  public User login(String userName, String password) {
    try {
    for(Map.Entry<UUID, User> entry : users.entrySet()) {
      if(entry.getValue().getUserName().equals(userName) && 
      entry.getValue().getPassword().equals(password)) {
        User user = entry.getValue();
        return user;
      }
    }
} catch(Exception e) {
    System.out.println("Incorrect username/password" );
}
    return null;
  }

  public UUID getUserID(String userName) {
    for(Map.Entry<UUID, User> entry : users.entrySet()) {
      if(entry.getValue().getUserName().equals(userName)) {
        UUID toReturn = entry.getValue().getUserID();
        return toReturn;
      }
    }
    System.out.println("User not found");
    return null;
  }
  public void saveUsers() {

  }
}
