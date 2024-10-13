import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * The class UserList maintains a collection of Users using a 
 * HashMap with UUID as the key and the User as the value;
 * @author Tony Lin and Ishaan Cheema
 */

public class UserList {
  private HashMap<UUID, User> users;
  private static UserList userList;
  private int size;

  private UserList() {
    users = new HashMap<>();
    size = 0;
  }

  public static synchronized UserList getInstance() {
    if (userList == null) {
      userList = new UserList();
    }
    return userList;
  }

  public User addUser(String firstName, String lastName,
                      String userName, String password, UUID userID) {
    if (users.containsKey(userID)) {
      System.out.println("User with this ID already exists.");
      return null;
    }
    User toAdd = new User(firstName, lastName, userName, password, userID);
    users.put(userID, toAdd);
    ++size;
    return toAdd;
  }

  public User getUser(UUID userID) {
    return users.get(userID);
  }

  public User editUser(UUID userID, int change, String updated) {
    User user = users.get(userID);
    if (user == null) {
      System.out.println("User not found.");
      return null;
    }

    switch (change) {
      case 0:
        user.setFirstName(updated);
        break;
      case 1:
        user.setLastName(updated);
        break;
      case 2:
        user.setUserName(updated);
        break;
      case 3:
        user.setPassword(updated);
        break;
      default:
        System.out.println("Invalid field to change.");
        return null;
    }
    return user;
  }

  public boolean removeUser(UUID userID) {
    if (users.containsKey(userID)) {
      users.remove(userID);
      --size;
      return true;
    } else {
      System.out.println("User not found.");
      return false;
    }
  }

  public User login(String userName, String password) {
    for (User user : users.values()) {
      if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
        return user;
      }
    }
    System.out.println("Incorrect username/password.");
    return null;
  }

  public UUID getUserID(String userName) {
    for (User user : users.values()) {
      if (user.getUserName().equals(userName)) {
        return user.getUserID();
      }
    }
    System.out.println("User not found.");
    return null;
  }

  public void saveUsers() {
    DataWriter.saveUsers();
  }

  public Integer getSize() {
    return this.size;
  }

  public ArrayList<User> getAllUsers() {
    return new ArrayList<>(users.values());
  }
}
