import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import javax.swing.plaf.multi.MultiPanelUI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The DataWriter is responsible for writing all stored data into 
 * the respective json files.
 */
public class DataWriter extends DataConstants{
  /**
   * Saves all users to user.json as well as all relevant
   * user information including: personal information, language
   * progress, friend list, and personal settings
   * @param userList
   */
  public static void saveUsers(ArrayList<User> userList) {
    try {
    LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();
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
      if(facade.getLanguage()!= null) {
      JSONArray languagesArray = new JSONArray();
      JSONObject spanishObject = new JSONObject();
      spanishObject.put(FOREIGN_LANGUAGE, String.valueOf(facade.getLanguage().getForeignLanguage()));
      spanishObject.put(LANGUAGE_PROGRESS, facade.getUser().getLanguageProgress());
      spanishObject.put(MODULE, 2);
      JSONArray troubleArray = new JSONArray();
      for(LessonTopic topic : LessonTopic.values()) {
        HashMap<Question,Integer> map = user.getTrouble().get(topic);
        for(Question question : map.keySet()) {
          JSONObject troubleObject = new JSONObject();
          troubleObject.put(TOPIC, topic);
          troubleObject.put(QUESTIONTYPE, getQuestionType(question));
          if(getQuestionType(question) instanceof FillBlank) {
            FillBlank toSave = (FillBlank)question;
            troubleObject.put(QUESTION_ID, toSave.getId());
          }
          else if(getQuestionType(question) instanceof MultipleChoice) {
            MultipleChoice toSave = (MultipleChoice) question;
            troubleObject.put(QUESTION_ID, toSave.getId());
          }
          troubleObject.put(INCORRECT, map.get(question));
          troubleArray.add(troubleObject);
        }
      }
      spanishObject.put(TROUBLE,troubleArray);
       JSONArray incompleteArray = new JSONArray();
       for(LessonTopic topic : LessonTopic.values()) {
         JSONObject incompleteObject = new JSONObject();
         JSONArray questionTypes = new JSONArray();
         for(Object object : user.getIncomplete(topic)) {
            if(object instanceof Matching) {
              questionTypes.add(MATCHING);
            }
            else if(object instanceof FillBlank) {
              questionTypes.add(FILLBLANK);
            }
            else if(object instanceof MultipleChoice) {
              questionTypes.add(MULTIPLECHOICE);
            }
            else if(object instanceof PictureStory) {
              questionTypes.add(STORY);
            }
            else if(object instanceof Flashcard) {
              questionTypes.add(FLASHCARD);
            }
         }
         switch(topic) {
          case SCHOOL: incompleteObject.put(TOPIC, SCHOOL); break;
          case FAMILY: incompleteObject.put(TOPIC, FAMILY); break;
          case WEATHER: incompleteObject.put(TOPIC, WEATHER); break;
          case FOOD: incompleteObject.put(TOPIC, FOOD); break;
          case PETS: incompleteObject.put(TOPIC, PETS); break;
         }
         incompleteObject.put(QUESTIONTYPE,questionTypes);
         incompleteArray.add(incompleteObject);
       }
       spanishObject.put(INCOMPLETE, incompleteArray);
       languagesArray.add(spanishObject);
       userJSON.put(USER_LANGUAGES,languagesArray);
      }
        usersJSON.add(userJSON);
    }    
      FileWriter writer = new FileWriter(USER_JSON);
      writer.write(usersJSON.toJSONString());
      writer.flush();
    } catch (Exception e) {
        System.out.println(e);
    }
  }

  /**
   * Returns a casted object of one of the lesson objects depending on 
   * what is passed in to differentiate the different lesson objects
   * @param object to be differentiated
   * @return casted version of the object 
   */
  public static Object getQuestionType(Object object) {
    if (object instanceof MultipleChoice) 
      return (MultipleChoice) object;
    else if (object instanceof FillBlank) 
      return (FillBlank) object;
    else if (object instanceof Matching) 
      return (Matching) object;
    else if (object instanceof Flashcard) 
      return (Flashcard) object;
    else if (object instanceof PictureStory) 
      return (PictureStory) object;
    return null;
  }

  public static void saveLanguages() {
  }
}