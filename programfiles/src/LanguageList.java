import java.util.HashMap;
import java.util.ArrayList;
import java.util.UUID;


/**
 * The LanguageList class manages a list of languages associated with
 * each user using a HashMap.
 * @author Tony Lin
 */
public class LanguageList {
  private static LanguageList languageList;
  private HashMap<UUID, ArrayList<Language>> userLanguages;

  private LanguageList() {
    userLanguages = new HashMap<UUID,ArrayList<Language>>();
  }

  public static LanguageList getInstance() {
    if(languageList == null)
      languageList = new LanguageList();
    return languageList;
  }

  public Language addLanguage(UUID userID, Language language) {
    if(!this.userLanguages.keySet().contains(userID))
      userLanguages.put(userID, new ArrayList<Language>());
    else
      this.userLanguages.get(userID).add(language);
    return language;
    }

//   public Language getLanguage(UUID userID, ForeignLanguage language) {  needs ForeignLanguage Enum
//     Language toReturn;
//     for(int i = 0; i < userLanguages.get(userID).size(); ++i) {
//       if(userLanguages.get(userID).get(i).getForeignLanguage().equals(language))
//         toReturn = userLanguages.get(userID).get(i).getForeignLanguage()
//     }
//       return toReturn;
//   }

  public ArrayList<Language> getAllLanguages(UUID userID) {
    return userLanguages.get(userID);
  }


}
