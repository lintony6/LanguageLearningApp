import java.util.HashMap;
import java.util.ArrayList;
/**
 * A class to manage a language learning dictionary of words and their
 * translations.
 */
public class DictionaryManager {
    private HashMap<LanguageDifficulty, HashMap<LessonTopic, HashMap<Word, String>>> data;
    private static DictionaryManager manager;
    
    public static DictionaryManager getInstance() {
        if(manager == null)
          manager = new DictionaryManager();
        return manager;
      }

    private DictionaryManager() {
        this.data = new HashMap<>();
    }

    /**
     * Adds a word to the dictionary.
     *
     * @param difficulty The difficulty level of the word.
     * @param category   The category of the word.
     * @param foreign    The foreign word.
     * @param english    The English translation of the word.
     */
    public void addWord(LanguageDifficulty difficulty, LessonTopic category, String foreign, String english,
            String meaning) {
        // Create the inner HashMap if it doesn't exist
        data.putIfAbsent(difficulty, new HashMap<>());
        data.get(difficulty).putIfAbsent(category, new HashMap<>());
        Word word = new Word(foreign, english);
        // Add the word and its translation to the appropriate category
        data.get(difficulty).get(category).put(word, meaning);
    }

    /**
     * Retrieves the translation of a word.
     *
     * @param difficulty The difficulty level of the word.
     * @param category   The category of the word.
     * @param foreign    The foreign word to translate.
     * @return The translation of the word, or "Translation not found." if the
     *         translation is not in the dictionary.
     */
    public String getMeaning(LanguageDifficulty difficulty, LessonTopic category, String foreign) {
        for (Word word : data.get(difficulty).get(category).keySet()) {
            if (word.getForeign().equalsIgnoreCase(foreign)) {
                return data.get(difficulty).get(category).get(word);
            }
        }
        return "Translation not found.";
    }
    
    public ArrayList<Word> getWordsByTopic(LanguageDifficulty difficulty, LessonTopic topic) {
      ArrayList<Word> wordList = new ArrayList();
      for(Word word : data.get(difficulty).get(topic).keySet()) {
        wordList.add(word);
      }
      return wordList;
    }
    /**
     * Retrieves the meaning of a word.
     *
     * @param difficulty The difficulty level of the word.
     * @param category   The category of the word.
     * @param foreign    The foreign word to get the meaning for.
     * @return A string containing the meaning of the word, or "Word not found." if
     *         the word is not in the dictionary.
     */
    public String getTranslation(LanguageDifficulty difficulty, LessonTopic category, String foreign) {
        for (Word word : data.get(difficulty).get(category).keySet()) {
            if (word.getForeign().equalsIgnoreCase(foreign)) {
                return "The word '" + foreign + "' translates to '" + word.getEnglish() + "' in English.";
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Example usage:
        DictionaryManager manager = new DictionaryManager();

        // Adding words directly to the dictionary
        manager.addWord(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "clase", "class",
                "A group of students meeting for instruction.");
        manager.addWord(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "libro", "book",
                "A set of written or printed pages, bound together.");

        // Retrieving translations
        System.out.println(manager.getTranslation(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "clase")); // Output:
                                                                                                          // class
        System.out.println(manager.getMeaning(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "libro")); // Output: The
                                                                                                      // word 'libro'
                                                                                                      // translates to
                                                                                                      // 'book' in
                                                                                                      // English.
        System.out.println(manager.getMeaning(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "mesa")); // Output: Word not
                                                                                                     // found.
    }
}