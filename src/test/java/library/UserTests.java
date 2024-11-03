package library;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * This class tests the functions of the User class using JUnit tests
 * @author Tony Lin
 */
public class UserTests {
  Lesson lesson = new Lesson(LanguageDifficulty.EASY, LessonTopic.FOOD);
  User bob = new User("Bob", "Smith", "bobbysmith", "bobbysmith", "bobbysmith@email.com", UUID.randomUUID());
  User amy = new User("Amy", "Horton", "amyhorton5", "ilovelearning", "amyhorton2000@email.com",UUID.randomUUID());
  User jeff = new User("Jeff","Hardy","jeffryhardy","jeffryhardy","jeff@email.com",UUID.randomUUID());
  @BeforeClass
  public void oneTimeSetup() {
  }
	
  @AfterClass
  public void oneTimeTearDown() {
		
  }
	
  @BeforeEach
  public void setup() {
    bob.addFriend(amy);
    amy.addFriend(bob);
    bob.addFriend(jeff);
    jeff.addFriend(bob);
    bob.setIncomplete(LessonTopic.FOOD, lesson.getQuestions());
  }
	
  @AfterEach
  public void tearDown() {
  }

  @Test
  public void testInvalidPassword() {
    User tony = new User("Tony", "Lin", "tonylin6", "1", "tonylin@email.com", UUID.randomUUID());
    assertFalse(tony instanceof User);
  }

  @Test 
  public void testInvalidEmail() {
    User tony = new User("Tony", "Lin", "tonylin6", "donkeysrcool", "bad", UUID.randomUUID());
    assertFalse(tony instanceof User);
  }

  @Test
  public void testValidUser() {
    User tony = new User("Tony", "Lin", "tonylin6", "donkeysrcool", "tonylin@email.com", UUID.randomUUID());
    assertTrue(tony instanceof User);
    }

  @Test
  public void testAddFriendValid() {
    assertEquals(amy, bob.getFriendList().get(0));
  }

  @Test
  public void testRemoveFriend() {
    bob.removeFriend(amy);
    assertNotEquals(amy,bob.getFriendList().get(0));
  }

  @Test
  public void testCorrect() {
    bob.correct(LessonTopic.FOOD, lesson.getMatching());
    boolean success = true;
    for(Object obj : bob.getIncomplete(LessonTopic.FOOD))
      if(obj instanceof Matching)
        success = false;
    assertTrue(success);
  }

}
