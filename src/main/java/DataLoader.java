import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.UUID;
import java.util.ArrayList;

/**
 * The DataLoader class is responsible for loading in all user data
 * including their personal data, their saved settings, and their 
 * friends list from a user.json file. This class is also responsible
 * for loading all of the user's languages, their progress in the 
 * languages, and the incomplete lessons remaining
 * @author Tony Lin
 */
public class DataLoader extends DataConstants{

    /**
     * Loads all users, their user settings, and their friends
     * list into a UserList and returns the full UserList
     * @return UserList containing all information from JSON file
     */
  public static UserList loadUsers() {
    try {
      UserList userList = UserList.getInstance();
      FileReader reader = new FileReader(USER_JSON);
      JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
      for (int i = 0; i < usersJSON.size(); ++i) {
        JSONObject userJSON = (JSONObject)usersJSON.get(i);
        JSONObject userSETTINGS = (JSONObject) userJSON.get(USER_SETTINGS);
        UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
        String firstName = (String) userJSON.get(USER_FIRST_NAME);
        String lastName = (String) userJSON.get(USER_LAST_NAME);
        String userName = (String) userJSON.get(USER_USERNAME);
        String password = (String) userJSON.get(USER_PASSWORD);
        String email = (String) userJSON.get(USER_EMAIL);
        userList.addUser(firstName, lastName, userName, password, email, userID);
        User user = userList.getUser(userID);
        Long longNum = (Long) userSETTINGS.get(USER_NOTIFICATIONS);
        int updated = longNum.intValue();
        user.changeSetting(0, updated);
        longNum = (Long) userSETTINGS.get(USER_LIGHT_MODE);
        updated = longNum.intValue();
        user.changeSetting(1, updated);
        longNum = (Long) userSETTINGS.get(USER_TEXT_TO_SPEECH);
        updated = longNum.intValue();
        user.changeSetting(2, updated);
        longNum = (Long) userSETTINGS.get(USER_FONT_SIZE);
        updated = longNum.intValue();
        user.changeSetting(3, updated);

      }
      for(int i = 0; i < usersJSON.size(); ++i) {
        JSONObject userJSON = (JSONObject)usersJSON.get(i);
        JSONArray userFRIENDS = (JSONArray)userJSON.get(USER_FRIENDS);
        if(userFRIENDS != null) {
        UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
        for(Object friend : userFRIENDS) {
          JSONObject friendJSON = (JSONObject) friend;
          UUID friendID = UUID.fromString((String)friendJSON.get(USER_ID));
          userList.getUser(userID).addFriend(userList.getUser(friendID));
        }
      }
    }
      return userList;
    } catch (Exception e) {
        System.out.println(e);
        return null;
      }
    }

  public static LanguageList loadLanguages() {
    try {
      LanguageList languageList = LanguageList.getInstance();
      FileReader reader = new FileReader(USER_JSON);
      JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
      for (int i = 0; i < usersJSON.size(); ++i) {
        JSONObject userJSON = (JSONObject)usersJSON.get(i);
      JSONArray languagesJSON = (JSONArray) userJSON.get(USER_LANGUAGES);
      if(languagesJSON != null) {
        for(Object language : languagesJSON) {
          JSONObject languageJSON = (JSONObject) language;
          LanguageDifficulty difficulty = LanguageDifficulty.fromString((String)languageJSON.get(DIFFICULTY));
          ForeignLanguage foreignLanguage = ForeignLanguage.fromString((String)languageJSON.get(FOREIGN_LANGUAGE));
          languageList.addLanguage(UUID.fromString((String)userJSON.get(USER_ID)), foreignLanguage, difficulty);
        }
      }
    }
    return languageList;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public static DictionaryManager loadDictionary() {
    try {
      DictionaryManager dictionaryManager = new DictionaryManager();
      FileReader reader = new FileReader(DICTIONARY_JSON);
      JSONArray jsonArray = (JSONArray)new JSONParser().parse(reader);
      for (Object obj : jsonArray) {
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray difficultyArray = (JSONArray) jsonObject.get(DIFFICULTY);
        for(Object difficultyObj : difficultyArray) {
          JSONObject difficultyLevel = (JSONObject)difficultyObj;
          LanguageDifficulty difficulty = LanguageDifficulty.fromString((String)difficultyLevel.get(DIFFICULTY));
          JSONArray wordsByTopicArray= (JSONArray) difficultyLevel.get(WORDSBYTOPIC);
            for(Object topicObj : wordsByTopicArray) {
              JSONObject topicEntry = (JSONObject) topicObj;
              LessonTopic topic = LessonTopic.fromString((String)topicEntry.get(TOPIC));
              JSONArray wordsArray = (JSONArray) topicEntry.get(WORDS);
              for(Object wordObj : wordsArray) {
                JSONObject wordEntry = (JSONObject) wordObj;
                String foreign = (String) wordEntry.get(SPANISH);
                String english = (String) wordEntry.get(ENGLISH);
                String meaning = (String) wordEntry.get(MEANING);
                dictionaryManager.addWord(difficulty, topic, foreign, english, meaning);
              }
            }
        }
    }
    return dictionaryManager;
   } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }
  }
    
    
    
    
    
    
    
    
    

    

    

    
    

    