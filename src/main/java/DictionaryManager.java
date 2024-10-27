import java.util.HashMap;

/**
 * A class to manage a language learning dictionary of words and their translations.
 */
public class DictionaryManager {
    private HashMap<LanguageDifficulty, HashMap<LessonTopic, Word>> data;

    public DictionaryManager(HashMap<LanguageDifficulty, HashMap<LessonTopic, Word>> data) {
        this.data = data;
    }

    /**
     * Retrieves the meaning of a word.
     *
     * @param difficulty The difficulty level of the word.
     * @param category The category of the word.
     * @return The meaning of the word, or "Word not found." if the word is not in the dictionary.
     */
    public String getWordMeaning(LanguageDifficulty difficulty, LessonTopic category) {
        if (data.containsKey(difficulty) && data.get(difficulty).containsKey(category)) {
            Word word = data.get(difficulty).get(category);
            return "Foreign: " + word.getForeign() + ", English: " + word.getEnglish();
        }
        return "Word not found.";
    }

    /**
     * Retrieves the translation of a word.
     *
     * @param difficulty The difficulty level of the word.
     * @param category The category of the word.
     * @param language The language to translate to.
     * @return The translation of the word, or "Translation not found." if the translation is not in the dictionary.
     */
    public String getTranslation(LanguageDifficulty difficulty, LessonTopic category, String language) {
        if (data.containsKey(difficulty) && data.get(difficulty).containsKey(category)) {
            Word word = data.get(difficulty).get(category);
            if (language.equalsIgnoreCase("spanish")) 
                return word.getForeign();
             else if (language.equalsIgnoreCase("english")) 
                return word.getEnglish();
        }
        return "Translation not found.";
    }
}

    public static void main(String[] args) {
        // Example usage:
        HashMap<LanguageDifficulty, HashMap<LessonTopic, Word>> data = new HashMap<>();
        DictionaryManager manager = new DictionaryManager(data);

        // Adding data directly to the data map for demonstration
        HashMap<LessonTopic, Word> easySchoolWords = new HashMap<>();
        easySchoolWords.put(LessonTopic.SCHOOL, new Word("clase", "class"));
    }
        