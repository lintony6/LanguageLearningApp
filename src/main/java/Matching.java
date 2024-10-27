import java.util.ArrayList;
import java.util.HashMap;

public class Matching {
    // Field to store word pairs
    private ArrayList<Word> wordPairs;

    // Constructor
    public Matching(ArrayList<Word> wordPairs) {
        this.wordPairs = wordPairs;
    }

    // Method to add a word pair
    public void addWordPair(Word word1, Word word2) {
        wordPairs.add(word1);
        wordPairs.add(word2);
    }

    // Method to remove a word pair
    public void removeWordPair(Word word1, Word word2) {
        wordPairs.remove(word1);
        wordPairs.remove(word2);
    }

    // Method to get the content of word pairs as a HashMap
    public HashMap<Word, Word> getContent() {
        HashMap<Word, Word> wordMap = new HashMap<>();
        for (int i = 0; i < wordPairs.size(); i += 2) {
            wordMap.put(wordPairs.get(i), wordPairs.get(i + 1));
        }
        return wordMap;
    }

    // Method to check if a word pair matches
    public boolean isCorrect(Word word1, Word word2) {
        HashMap<Word, Word> wordMap = getContent();
        return wordMap.getOrDefault(word1, null) == word2;
    }
}
