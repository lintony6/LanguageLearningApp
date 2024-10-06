import java.util.UUID;
import java.util.ArrayList;


/**
 * The class LanguageLearningSystemFacade represents a facade for a 
 * language learning program that has a static BoardGame, Facade,
 * LanguageList, UserList, and has a user. This class will be the 
 * only class the User interacts with
 * @author Tony Lin
 */
public class LanguageLearningSystemFacade {
  private User user;
  //private static BoardGame boardGame;
  private static LanguageLearningSystemFacade facade;
  private static LanguageList languageList;
  //private static UserList userList;

  private LanguageLearningSystemFacade() {
    //userList = UserList.getInstance();
    LanguageList.getInstance();
    //boardGame = BoardGame.getInstance();
  }

  public static LanguageLearningSystemFacade getInstance() {
    if(facade == null)
      facade = new LanguageLearningSystemFacade();
    return facade;
  }


//   public User login(String userName, String password) {
//     return userList.login(userName,password);
//   }

//   public boolean logout() {
//     userList.saveUsers();
//     boardGame.saveBoard();
//     languageList.saveLanguages();
//   }

  public User signUp(String firstName, String lastName,
                     String userName, String password) {
    this.user = new User(firstName, lastName, userName, password);
    return user;
  }

  public User editUser(int change, String updated) {
    //userList.editUser(this.user.getUserID(),change,updated);
    return this.user;
  }

//   public User addFriend(String userName) {
//     UUID friendID = userList.getUserID(userName);
//     User friend = userList.getUser();
//     userList.getUser(this.user.getUserID()).addFriend(friend);
//     return friend;
//   }

// public ArrayList<User> getFriendList() {
//   return userList.getUser(this.user).getFriendList();
// }

//   public boolean removeFriend(String userName) {
//     UUID friendID = userList.getUserID(userName);
//     User friend = userList.getUser(friendID);
//     userList.getUser(this.user.getUserID()).removeFriend(friend);
//   }
  
  public void changeSettingOptions(int setting, int updated) {
    //userList.getUser(this.user.getUserID()).changeSettings(setting, updated);
  }

  public ArrayList<Language> getLanguages() {
    return languageList.getAllLanguages(this.user.getUserID());
  }

//   public BoardGame startGame() {
//     boardGame.startGame();
//     return boardGame;
//   }

//   public Lesson chooseTopic(LessonTopic topic) {
//     return languageList.getLanguage(this.user.getUserID()).getLesson(topic);
// }

//   public Lesson startLesson(Lesson lesson) {
//     Lesson toreturn = languageList.getLanguage(this.user.getUserID()).getLesson(lesson.getTopic());
//   }

//   public boolean endLesson(Lesson lesson) {
//     languageList.getLanguage(this.user.getUserID()).getLesson(lesson).completeLesson();
//   }



}
