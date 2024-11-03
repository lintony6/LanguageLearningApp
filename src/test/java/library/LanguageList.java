package library;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

class LanguageListTest {
    private LanguageList languageList;
    private UUID userID;
    private ForeignLanguage language1;
    private ForeignLanguage language2;
    private LanguageDifficulty difficulty;

    @BeforeEach
    void setUp() {
        languageList = LanguageList.getInstance(); // Singleton instance
        userID = UUID.randomUUID();
        language1 = ForeignLanguage.SPANISH; // Assuming ForeignLanguage is an enum or constant
        language2 = ForeignLanguage.FRENCH;
        difficulty = LanguageDifficulty.INTERMEDIATE; // Assuming LanguageDifficulty is an enum or constant
    }

    @Test
    void testAddLanguage() {
        Language addedLanguage = languageList.addLanguage(userID, language1, difficulty);
        assertNotNull(addedLanguage);
        assertEquals(language1, addedLanguage.getForeignLanguage());
        assertEquals(difficulty, addedLanguage.getDifficulty());
    }

    @Test
    void testGetLanguage() {
        languageList.addLanguage(userID, language1, difficulty);
        Language retrievedLanguage = languageList.getLanguage(userID, language1);
        assertNotNull(retrievedLanguage);
        assertEquals(language1, retrievedLanguage.getForeignLanguage());
    }

    @Test
    void testGetLanguage_NotAdded() {
        languageList.addLanguage(userID, language1, difficulty);
        Language retrievedLanguage = languageList.getLanguage(userID, language2);
        assertNull(retrievedLanguage);
    }

    @Test
    void testGetAllLanguages() {
        languageList.addLanguage(userID, language1, difficulty);
        languageList.addLanguage(userID, language2, difficulty);
        ArrayList<Language> allLanguages = languageList.getAllLanguages(userID);
        assertEquals(2, allLanguages.size());
        assertTrue(allLanguages.stream().anyMatch(lang -> lang.getForeignLanguage().equals(language1)));
        assertTrue(allLanguages.stream().anyMatch(lang -> lang.getForeignLanguage().equals(language2)));
    }
}
