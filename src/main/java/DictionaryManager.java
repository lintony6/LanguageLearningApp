import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class to manage a language learning dictionary of words and their translations.
 */
public class DictionaryManager {
    private HashMap<String, HashMap<String, Category>> data;

    public DictionaryManager(HashMap<String, HashMap<String, Category>> data) {
        this.data = data;
    }

    /**
     * Adds a word, its meaning, and its translation to the dictionary.
     *
     * @param difficulty The difficulty level of the word.
     * @param category The category of the word.
     * @param word The word to add.
     * @param meaning The meaning of the word.
     * @param translation The translation of the word.
     */
    public void addWord(String difficulty, String category, String word, String meaning, Translation translation) {
        data.putIfAbsent(difficulty, new HashMap<>());
        data.get(difficulty).putIfAbsent(category, new Category(meaning));

        Category cat = data.get(difficulty).get(category);
        cat.addRelatedWord(word);
        cat.addTranslation(translation.getLanguage(), translation.getWord());
    }

    /**
     * Retrieves the meaning of a word.
     *
     * @param difficulty The difficulty level of the word.
     * @param category The category of the word.
     * @param word The word to retrieve the meaning for.
     * @return The meaning of the word, or "Word not found." if the word is not in the dictionary.
     */
    public String getWordMeaning(String difficulty, String category, String word) {
        if (data.containsKey(difficulty) && data.get(difficulty).containsKey(category)) {
            if (data.get(difficulty).get(category).getRelatedWords().contains(word)) {
                return data.get(difficulty).get(category).getMeaning();
            }
        }
        return "Word not found.";
    }

    /**
     * Retrieves the translation of a word.
     *
     * @param difficulty The difficulty level of the word.
     * @param category The category of the word.
     * @param word The word to retrieve the translation for.
     * @param language The language to translate to.
     * @return The translation of the word, or "Translation not found." if the translation is not in the dictionary.
     */
    public String getTranslation(String difficulty, String category, String word, String language) {
        if (data.containsKey(difficulty) && data.get(difficulty).containsKey(category)) {
            if (data.get(difficulty).get(category).getRelatedWords().contains(word)) {
                return data.get(difficulty).get(category).getTranslations().getOrDefault(language, "Translation not found.");
            }
        }
        return "Translation not found.";
    }

    /**
     * Retrieves all related words in a category.
     *
     * @param difficulty The difficulty level of the word.
     * @param category The category of the word.
     * @return A list of related words in the category.
     */
    public ArrayList<String> getRelatedWords(String difficulty, String category) {
        if (data.containsKey(difficulty) && data.get(difficulty).containsKey(category)) {
            return data.get(difficulty).get(category).getRelatedWords();
        }
        return new ArrayList<>();
    }

    /**
     * Removes a word from the dictionary.
     *
     * @param difficulty The difficulty level of the word.
     * @param category The category of the word.
     * @param word The word to remove.
     * @return A message indicating the result of the removal.
     */
    public String removeWord(String difficulty, String category, String word) {
        if (data.containsKey(difficulty) && data.get(difficulty).containsKey(category)) {
            if (data.get(difficulty).get(category).getRelatedWords().contains(word)) {
                data.get(difficulty).get(category).getRelatedWords().remove(word);
                return word + " removed from " + difficulty + " - " + category + ".";
            }
        }
        return "Word not found.";
    }

    public static void main(String[] args) {
        // Example usage:
        HashMap<String, HashMap<String, Category>> data = new HashMap<>();
        DictionaryManager manager = new DictionaryManager(data);

        // Adding a word
        manager.addWord("easy", "school", "pencil", "A tool used for writing or drawing.", new Translation("spanish", "lápiz"));

        // Getting a meaning
        System.out.println(manager.getWordMeaning("easy", "school", "teacher"));

        // Getting a translation
        System.out.println(manager.getTranslation("easy", "school", "pencil", "spanish"));

        // Getting related words
        System.out.println(manager.getRelatedWords("easy", "school"));

        // Removing a word
        System.out.println(manager.removeWord("easy", "school", "teacher"));
    }
}

/**
 * A class representing a category in the dictionary.
 */
class Category {
    private String meaning;
    private ArrayList<String> relatedWords;
    private HashMap<String, String> translations;

    public Category(String meaning) {
        this.meaning = meaning;
        this.relatedWords = new ArrayList<>();
        this.translations = new HashMap<>();
    }

    public String getMeaning() {
        return meaning;
    }

    public ArrayList<String> getRelatedWords() {
        return relatedWords;
    }

    public HashMap<String, String> getTranslations() {
        return translations;
    }

    public void addRelatedWord(String word) {
        relatedWords.add(word);
    }

    public void addTranslation(String language, String word) {
        translations.put(language, word);
    }
}

/**
 * A class representing a translation.
 */
class Translation {
    private String language;
    private String word;

    public Translation(String language, String word) {
        this.language = language;
        this.word = word;
    }

    public String getLanguage() {
        return language;
    }

    public String getWord() {
        return word;
    }
}
