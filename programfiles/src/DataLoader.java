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
  public static ArrayList<User> loadUsers(String filePath) {
    try {
      ArrayList<User> userList = new ArrayList<User>();
      FileReader reader = new FileReader(filePath);
      JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
      for (int i = 0; i < usersJSON.size(); ++i) {
        JSONObject userJSON = (JSONObject)usersJSON.get(i);
        UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
        String firstName = (String) userJSON.get(USER_FIRST_NAME);
        String lastName = (String) userJSON.get(USER_LAST_NAME);
        String userName = (String) userJSON.get(USER_USERNAME);
        String password = (String) userJSON.get(USER_PASSWORD);
        User user = new User(firstName,lastName,userName,password,userID);
        userList.add(user);
      }
      return userList;
    } catch (Exception e) {
        System.out.println(e);
        return null;
      }
    }
}

    
    
    
    
    
    
    
    
    

    

    

    
    

    