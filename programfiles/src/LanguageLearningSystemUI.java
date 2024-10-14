import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

/**
 * LanguageLearningSystemUI is the user interface for the Language Learning System.
 * It provides functionality for user login, sign-up, displaying friends, 
 * selecting languages, and displaying lesson information.
 */
public class LanguageLearningSystemUI {
    private LanguageLearningSystemFacade facade; // Facade for the language learning system
    private JFrame frame; // Main application window
    private JTextField userNameField; // Field for username input
    private JPasswordField passwordField; // Field for password input
    private JTextField firstNameField; // Field for first name input
    private JTextField lastNameField; // Field for last name input
    private JTextField signUpUserNameField; // Field for sign-up username input
    private JPasswordField signUpPasswordField; // Field for sign-up password input
    private JTextArea friendListArea; // Area to display user's friends
    private JComboBox<Language> languageListBox; // Dropdown to select languages
    private JTextArea lessonArea; // Area to display lesson information

    /**
     * Constructs the LanguageLearningSystemUI and initializes the facade and UI components.
     */
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
        User user = facade.login(userName, password);
        if (user != null) {
            JOptionPane.showMessageDialog(frame, "Login Successful!");
            loadUserFriends();
            loadUserLanguages();
            loadLesson();
        } else {
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

        // Perform sign-up using the facade
        if(password.length() < 8) {
            JOptionPane.showMessageDialog(frame, "Sign-Up Failed! Please try again.");
        }
        else {
            User newUser = facade.signUp(firstName, lastName, userName, password, UUID.randomUUID());
            JOptionPane.showMessageDialog(frame, "Sign-Up Successful!");
            loadUserFriends();
            loadUserLanguages();
            loadLesson();
            
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
