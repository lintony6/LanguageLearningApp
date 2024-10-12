import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The DataLoader class is responsible for loading data from a file.
 * It reads the file, parses the data, and returns a list of User objects.
 */
public class DataLoader extends DataConstants{

    /**
     * Loads data from the opened file.
     * 
     * @return A list of User objects.
     * @throws IOException If the file is not open or an I/O error occurs.
     */
  public static UserList loadUsers(String filePath) {
    try {
      UserList userList = UserList.getInstance();
      FileReader reader = new FileReader(filePath);
      JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
      for (int i = 0; i < usersJSON.size(); ++i) {
        JSONObject userJSON = (JSONObject)usersJSON.get(i);
        JSONObject userSETTINGS = (JSONObject) userJSON.get(USER_SETTINGS);
        UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
        String firstName = (String) userJSON.get(USER_FIRST_NAME);
        String lastName = (String) userJSON.get(USER_LAST_NAME);
        String userName = (String) userJSON.get(USER_USERNAME);
        String password = (String) userJSON.get(USER_PASSWORD);
        userList.addUser(firstName, lastName, userName, password,userID);
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
        UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
        for(Object friend : userFRIENDS) {
          JSONObject friendJSON = (JSONObject) friend;
          UUID friendID = UUID.fromString((String)friendJSON.get(USER_ID));
          userList.getUser(userID).addFriend(userList.getUser(friendID));
        }
      }
      return userList;
    } catch (Exception e) {
        System.out.println(e);
        return null;
      }
    }
}

    
    
    
    
    
    
    
    
    

    

    

    
    

    