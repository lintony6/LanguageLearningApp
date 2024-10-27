import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.ArrayList;

public class DataWriter extends DataConstants{
  public static void saveUsers() {
    UserList users = UserList.getInstance();
    ArrayList<User> userList = users.getAllUsers();
    JSONArray usersJSON = new JSONArray();
    for(User user : userList) {
      JSONObject userJSON = new JSONObject();
      userJSON.put(USER_ID, user.getUserID().toString());
      userJSON.put(USER_FIRST_NAME, user.getFirstName());
      userJSON.put(USER_LAST_NAME, user.getLastName());
      userJSON.put(USER_USERNAME, user.getUserName());
      userJSON.put(USER_PASSWORD, user.getPassword());
      userJSON.put(USER_EMAIL, user.getEmail());
      JSONArray friendsJSON = new JSONArray();
      ArrayList<User> friendList = user.getFriendList();
      for(User friend : friendList) {
        JSONObject friendJSON = new JSONObject();
        friendJSON.put(USER_ID, friend.getUserID().toString());
        friendJSON.put(USER_USERNAME, friend.getUserName());
        friendsJSON.add(friendJSON);
      }
      userJSON.put(USER_FRIENDS, friendsJSON);
      JSONObject userSettings = new JSONObject();
      userSettings.put(USER_NOTIFICATIONS, Integer.valueOf(user.getSettings().getNotifications()));
      userSettings.put(USER_LIGHT_MODE, Integer.valueOf(user.getSettings().getLightMode()));
      userSettings.put(USER_TEXT_TO_SPEECH, Integer.valueOf(user.getSettings().getTextToSpeech()));
      userSettings.put(USER_FONT_SIZE, Integer.valueOf(user.getSettings().getFontSize()));
      userJSON.put(USER_SETTINGS, userSettings);
       usersJSON.add(userJSON);
    }    
    try {
      FileWriter writer = new FileWriter(FILE_PATH);
      writer.write(usersJSON.toJSONString());
      writer.flush();
    } catch (Exception e) {
        System.out.println(e);
    }
  }

  public static void saveLanguages() {
    UserList userList = UserList.getInstance();
    ArrayList<User> users = userList.getAllUsers();
    JSONArray languagesJSON = new JSONArray();
    LanguageList languages = LanguageList.getInstance();
    for(User user : users) {
    ArrayList<Language> userLanguages = languages.getAllLanguages(user.getUserID());
    for(Language language : userLanguages) {
      JSONObject languageJSON = new JSONObject();
      languageJSON.put(FOREIGN_LANGUAGE, language.getForeignLanguage()); 
      languageJSON.put(LANGUAGE_DIFFICULTY, language.getDifficulty());
      languageJSON.put(LESSONS_COMPLETED, language.getLanguageProgress());
      languagesJSON.add(languageJSON);
    }
    userJSON.put(USER_LANGUAGES, languagesJSON);
  }
    try {
      FileWriter writer = new FileWriter(FILE_PATH);
      writer.write(languagesJSON.toJSONString());
      writer.flush();
    } catch (Exception e) {
      System.out.println(e);
      }
  }
}
