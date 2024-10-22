import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//@author De'Juan Carson

public class LanguageLearningSystemUI extends JFrame {
    private JTextField usernameField, firstNameField, lastNameField, addFriendField, signUpUsernameField;
    private JPasswordField passwordField, signUpPasswordField, editPasswordField;
    private JTextArea friendListArea, languagesArea, lessonArea;
    private JComboBox<String> languageDropdown, difficultyDropdown;
    private JButton loginButton, signUpButton, logoutButton, editProfileButton, viewLanguagesButton, startGameButton,
            addFriendButton, removeFriendButton, saveChangesButton, startLanguageButton, chooseTopicButton,
            startLessonButton, endLessonButton, backButton, signUpMenuButton, submitEditButton, lessonTopicsButton,
            friendListButton, gameBackButton, friendListBackButton, lessonBackButton; // Declare lessonBackButton

    private String firstName = ""; // User's first name
    private String lastName = ""; // User's last name
    private String userName = ""; // User's username
    private String password = ""; // User's password

    public LanguageLearningSystemUI() {
        // Initialize the facade using the singleton pattern
        facade = LanguageLearningSystemFacade.getInstance();
        initializeUI();
    }

    /**
     * Initializes the user interface components and layout.
     */
    private void initializeUI() {
        frame = new JFrame("Language Learning System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(6, 1));

        // Panel for login
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel userNameLabel = new JLabel("Username:");
        userNameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        loginPanel.add(userNameLabel);
        loginPanel.add(userNameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);

        // Panel for sign-up
        JPanel signUpPanel = new JPanel(new GridLayout(5, 2));
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        JLabel signUpUserNameLabel = new JLabel("Username:");
        signUpUserNameField = new JTextField();
        JLabel signUpPasswordLabel = new JLabel("Password:");
        signUpPasswordField = new JPasswordField();
        JButton signUpButton = new JButton("Sign Up");
        signUpPanel.add(firstNameLabel);
        signUpPanel.add(firstNameField);
        signUpPanel.add(lastNameLabel);
        signUpPanel.add(lastNameField);
        signUpPanel.add(signUpUserNameLabel);
        signUpPanel.add(signUpUserNameField);
        signUpPanel.add(signUpPasswordLabel);
        signUpPanel.add(signUpPasswordField);
        signUpPanel.add(new JLabel(""));
        signUpPanel.add(signUpButton);

        // Panel for friends list
        JPanel friendPanel = new JPanel(new BorderLayout());
        JLabel friendLabel = new JLabel("Friends List:");
        friendListArea = new JTextArea(10, 30);
        JScrollPane friendScrollPane = new JScrollPane(friendListArea);
        friendPanel.add(friendLabel, BorderLayout.NORTH);
        friendPanel.add(friendScrollPane, BorderLayout.CENTER);

        // Panel for language list
        JPanel languagePanel = new JPanel(new BorderLayout());
        JLabel languageLabel = new JLabel("Languages:");
        languageListBox = new JComboBox<>(); // Changed to JComboBox<Language>
        languagePanel.add(languageLabel, BorderLayout.NORTH);
        languagePanel.add(languageListBox, BorderLayout.CENTER);

        // Panel for lessons
        JPanel lessonPanel = new JPanel(new BorderLayout());
        JLabel lessonLabel = new JLabel("Lesson:");
        lessonArea = new JTextArea(5, 30);
        lessonPanel.add(lessonLabel, BorderLayout.NORTH);
        lessonPanel.add(lessonArea, BorderLayout.CENTER);

        // Add all panels to frame
        frame.add(loginPanel);
        frame.add(signUpPanel);
        frame.add(friendPanel);
        frame.add(languagePanel);
        frame.add(lessonPanel);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Action listener for sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp();
            }
        });

        frame.setVisible(true);
    }

    /**
     * Handles the login action for the user. It retrieves the username and password
     * from the input fields and attempts to log the user in through the facade.
     * Displays success or failure message.
     */
    private void handleLogin() {
        String userName = userNameField.getText();
        String password = new String(passwordField.getPassword());

        // Perform login using the facade
        try {
            User user = facade.login(userName, password);
            loadUserFriends();
            loadUserLanguages();
            loadLesson();
            JOptionPane.showMessageDialog(frame, "Login Successful!");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(frame, "Login Failed! Please try again.");
        }      
    }

    /**
     * Handles the sign-up action for a new user. It retrieves the user's information
     * from the input fields and attempts to create a new user through the facade.
     * Displays success or failure message.
     */
    private void handleSignUp() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String userName = signUpUserNameField.getText();
        String password = new String(signUpPasswordField.getPassword());

        // Use try catch blocks when catching errors instead of if else
        try {
            User newUser = facade.signUp(firstName, lastName, userName, password, UUID.randomUUID());
            JOptionPane.showMessageDialog(frame, "Sign-Up Successful!");
            loadUserFriends();
            loadUserLanguages();
            loadLesson();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Sign-Up Failed! Please try again.");
        }
    }

    /**
     * Loads the user's friends from the facade and displays them in the friend list area.
     */
    private void loadUserFriends() {
        // Clear existing friends
        friendListArea.setText("");

        // Load friend list from facade
        for (User friend : facade.getFriendList()) {
            friendListArea.append(friend.getUserName());
        }
    }

    /**
     * Loads the languages that the user has started from the facade
     * and displays them in the language selection dropdown.
     */
    private void loadUserLanguages() {
        // Clear existing languages
       // languageListBox.removeAllItems();

        // Load languages from facade
        //for (Language language : facade.getLanguages()) {
        //    languageListBox.addItem(language); // Add the language object directly
        //}
    }

    /**
     * Loads and displays lesson information in the lesson area.
     * In a real implementation, you'd pull lesson details from the facade.
     */
    private void loadLesson() {
      //  lessonArea.setText("Lesson information goes here...");
        // In a real implementation, you'd pull lesson details from the facade
    }

    /**
     * Main method to launch the Language Learning System UI.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LanguageLearningSystemUI());
    }
} 