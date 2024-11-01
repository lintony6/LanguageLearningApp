package library;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import library.User;
public class UserTests {
  @BeforeClass
  public void oneTimeSetup() {
		
  }
	
  @AfterClass
  public void oneTimeTearDown() {
		
  }
	
  @BeforeEach
  public void setup() {
  	//runs before each test
  }
	
  @AfterEach
  public void tearDown() {
	//runs after each test
  }

  @Test
  public void testValidUser() {
    User user = new User("tony","lin","tonylin","password123","tony@email.com",UUID.randomUUID());
    assertEquals(user,user);
    }
}
