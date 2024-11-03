package library;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

class UserListTest {
    private UserList userList;

    @BeforeEach
    void setUp() {
        userList = UserList.getInstance(); // Singleton instance
    }

    @Test
    void testAddUser() {
        UUID userID = UUID.randomUUID();
        User user = userList.addUser("John", "Doe", "jdoe", "password123", "jdoe@example.com", userID);
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals(userID, user.getUserID());
    }

    @Test
    void testGetUser() {
        UUID userID = UUID.randomUUID();
        userList.addUser("Jane", "Doe", "janedoe", "securepass", "jane@example.com", userID);
        User user = userList.getUser(userID);
        assertNotNull(user);
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    void testEditUser() {
        UUID userID = UUID.randomUUID();
        userList.addUser("Sam", "Smith", "ssmith", "samspass", "sam@example.com", userID);
        userList.editUser(userID, 1, "Johnson"); // Edit last name
        User user = userList.getUser(userID);
        assertEquals("Johnson", user.getLastName());
    }

    @Test
    void testRemoveUser() {
        UUID userID = UUID.randomUUID();
        userList.addUser("Alex", "Brown", "abrown", "alexpass", "alex@example.com", userID);
        boolean removed = userList.removeUser(userID);
        assertTrue(removed);
        assertNull(userList.getUser(userID));
    }

    @Test
    void testLogin() {
        UUID userID = UUID.randomUUID();
        userList.addUser("Chris", "White", "cwhite", "chrispass", "chris@example.com", userID);
        User user = userList.login("cwhite", "chrispass");
        assertNotNull(user);
        assertEquals("Chris", user.getFirstName());
    }

    @Test
    void testGetUserID() {
        UUID userID = UUID.randomUUID();
        userList.addUser("Katie", "Green", "kgreen", "kpass", "katie@example.com", userID);
        UUID retrievedID = userList.getUserID("kgreen");
        assertEquals(userID, retrievedID);
    }

    @Test
    void testGetSize() {
        int initialSize = userList.getSize();
        userList.addUser("Leo", "Black", "lblack", "leopass", "leo@example.com", UUID.randomUUID());
        assertEquals(initialSize + 1, userList.getSize());
    }
}
