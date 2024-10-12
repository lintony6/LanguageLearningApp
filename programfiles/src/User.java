import java.util.UUID;
import java.util.ArrayList;

/**
 * The User class represents a user with data members such as
 * userID, firstName, lastName, userName, password, friendList,
 * and settings.
 * @author Tony Lin
 */
public class User {
  private UUID userID;
  private String firstName;
  private String lastName;
  private String userName;
  private String password;
  private ArrayList<User> friendList;
  private Settings settings;

/**
 * Parameterized constructor of user that generates random userID,
 * creates new empty friendList, and sets all of the data members,
 * to the arguments
 * @param firstName First name of the user
 * @param lastName Last name of the user
 * @param userName User name of the user
 * @param password Password of the user
 */
  public User(String firstName, String lastName, String userName,
              String password, UUID userID) {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.friendList = new ArrayList<User>();
    this.settings = new Settings();
  }
  
  /** Returns this user's userid
   * @return UUID of this user
   */
  public UUID getUserID() {
    return this.userID;
  }
  
  /** Sets this user's first name to the argument first name if
   * it is non-null
   * @param firstName first name to be set as user's first name
   */
  public void setFirstName(String firstName) {
    if(firstName != null)
      this.firstName = firstName;
  }

  /** Returns this users first name
   * @return String first name of this user
   */
  public String getFirstName() {
    return this.firstName;
  }

  /** Sets this user's last name to the argument last name if
   * it is non-null
   * @param lastName to be set as user's last name
   */
  public void setLastName(String lastName) {
    if(lastName != null)
      this.lastName = lastName;
  }
  
  /** Returns this user's last name
   * @return String last name of this user
   */
  public String getLastName() {
    return this.lastName;
  }

  /** Sets this user's username to the argument userName if 
   * it is non-null
   * @param userName to be set as the new userName
   */
  public void setUserName(String userName) {
    if(userName != null)
      this.userName = userName;
  }
  
  /** Returns this user's username
   * @return String username of this user
   */
  public String getUserName() {
    return this.userName;
  }

  /** Sets new password if the argument password is greater than 8
   * characters
   * @param String password to be set as the new password
   * @return boolean true if successful and false if unsuccessful
   */
  public boolean setPassword(String password) {
    if(password.length() >= 8) {
      this.password = password;
      return true;
    }
    return false;
  }
  
  /** Returns this user's password
   * @return String password of this user
   */
  public String getPassword() {
    return this.password;
  }

  /** Returns the friend list of this user
   * @return ArrayList<User> friendlist of this user
   */
  public ArrayList<User> getFriendList() {
    return this.friendList;
  }
  
  /** Adds the argument user onto this user's friendlist if the
   * argument user is non-null and not this user.
   * @param user to be added onto friend list
   * @return bool true if successfully added and false if unable to
   * add.
   */
  public boolean addFriend(User user) {
    if(user != null && user != this) {
      this.friendList.add(user);
      return true;
    }
    return false;
  }

  /** Checks if the argument user is a friend in the friend list and
   * removes it from the list if on there
   * @param user to be removed from the friend list
   * @return boolean true if the user is removed, false if friend is
   * not in the list.
   */
  public boolean removeFriend(User user) {
    if(user != null && user != this) {
        if(this.friendList.contains(user)) {
        this.friendList.remove(user);
        return true;
        }
      }
      return false;
  }

  /** Returns this user's current settings
   * @return Settings of this user
   */
  public Settings getSettings() {
    return this.settings;
  }

  /** Changes this users settings if the arguments are valid
   * @param setting The setting to change
   * @param updated 0 for false and anythin else for true;
   */
  public void changeSetting(int setting, int updated) {
    if((setting >= 0 && setting <= 3) && (updated >= 0)) {
      switch(setting) {
        case 0:this.settings.setNotifications(updated); break;
        case 1:this.settings.setLightMode(updated); break;
        case 2:this.settings.setTextToSpeech(updated); break;
        case 3:this.settings.setFontSize(updated);
      }
    }
  }
}
