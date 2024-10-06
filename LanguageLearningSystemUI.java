import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageLearningSystemUI extends JFrame {
    private JTextField usernameField, firstNameField, lastNameField, addFriendField;
    private JPasswordField passwordField;
    private JTextArea friendListArea, languagesArea, lessonArea;
    private JComboBox<String> languageDropdown, difficultyDropdown, topicDropdown;
    private JButton loginButton, signUpButton, logoutButton, editProfileButton, viewLanguagesButton, startGameButton,
            addFriendButton, removeFriendButton, saveChangesButton, startLanguageButton, chooseTopicButton,
            startLessonButton, endLessonButton;

    public LanguageLearningSystemUI() {
        setTitle("Language Learning System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        // Login Screen
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signUpButton);

        // Sign Up Screen
        JPanel signUpPanel = new JPanel(new GridLayout(4, 2));
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        signUpPanel.add(new JLabel("First Name:"));
        signUpPanel.add(firstNameField);
        signUpPanel.add(new JLabel("Last Name:"));
        signUpPanel.add(lastNameField);
        signUpPanel.add(new JLabel("Username:"));
        signUpPanel.add(usernameField);
        signUpPanel.add(new JLabel("Password:"));
        signUpPanel.add(passwordField);
        signUpPanel.add(signUpButton);

        // Main Dashboard
        JPanel dashboardPanel = new JPanel(new GridLayout(3, 2));
        logoutButton = new JButton("Logout");
        editProfileButton = new JButton("Edit Profile");
        viewLanguagesButton = new JButton("View Languages");
        startGameButton = new JButton("Start Game");
        dashboardPanel.add(new JLabel("Welcome, [Username]!"));
        dashboardPanel.add(logoutButton);
        dashboardPanel.add(editProfileButton);
        dashboardPanel.add(viewLanguagesButton);
        dashboardPanel.add(startGameButton);

        // Edit Profile Screen
        JPanel editProfilePanel = new JPanel(new GridLayout(4, 2));
        saveChangesButton = new JButton("Save Changes");
        editProfilePanel.add(new JLabel("Edit User Information:"));
        editProfilePanel.add(new JTextField());
        editProfilePanel.add(saveChangesButton);

        // Friend List Screen
        JPanel friendListPanel = new JPanel(new GridLayout(3, 2));
        friendListArea = new JTextArea();
        addFriendField = new JTextField();
        addFriendButton = new JButton("Add Friend");
        removeFriendButton = new JButton("Remove Friend");
        friendListPanel.add(new JLabel("Friend List:"));
        friendListPanel.add(new JScrollPane(friendListArea));
        friendListPanel.add(new JLabel("Add Friend:"));
        friendListPanel.add(addFriendField);
        friendListPanel.add(addFriendButton);
        friendListPanel.add(removeFriendButton);

        // Settings Screen
        JPanel settingsPanel = new JPanel(new GridLayout(2, 2));
        settingsPanel.add(new JLabel("Settings Options:"));
        settingsPanel.add(new JCheckBox("Option 1"));
        settingsPanel.add(new JCheckBox("Option 2"));

        // Languages Screen
        JPanel languagesPanel = new JPanel(new GridLayout(3, 2));
        languagesArea = new JTextArea();
        languageDropdown = new JComboBox<>(new String[] { "Language 1", "Language 2" });
        difficultyDropdown = new JComboBox<>(new String[] { "Easy", "Medium", "Hard" });
        startLanguageButton = new JButton("Start Language");
        languagesPanel.add(new JLabel("Available Languages:"));
        languagesPanel.add(new JScrollPane(languagesArea));
        languagesPanel.add(languageDropdown);
        languagesPanel.add(difficultyDropdown);
        languagesPanel.add(startLanguageButton);

        // Game Screen
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(new JLabel("Board Game Interface"), BorderLayout.CENTER);
        startGameButton = new JButton("Start Game");
        gamePanel.add(startGameButton, BorderLayout.SOUTH);

        // Lesson Screen
        JPanel lessonPanel = new JPanel(new GridLayout(3, 2));
        lessonArea = new JTextArea();
        topicDropdown = new JComboBox<>(new String[] { "Topic 1", "Topic 2" });
        chooseTopicButton = new JButton("Choose Topic");
        startLessonButton = new JButton("Start Lesson");
        endLessonButton = new JButton("End Lesson");
        lessonPanel.add(new JLabel("Choose Topic:"));
        lessonPanel.add(topicDropdown);
        lessonPanel.add(chooseTopicButton);
        lessonPanel.add(new JLabel("Current Lesson:"));
        lessonPanel.add(new JScrollPane(lessonArea));
        lessonPanel.add(startLessonButton);
        lessonPanel.add(endLessonButton);

        // Add panels to CardLayout
        add(loginPanel, "Login");
        add(signUpPanel, "Sign Up");
        add(dashboardPanel, "Dashboard");
        add(editProfilePanel, "Edit Profile");
        add(friendListPanel, "Friend List");
        add(settingsPanel, "Settings");
        add(languagesPanel, "Languages");
        add(gamePanel, "Game");
        add(lessonPanel, "Lesson");

        // Show login panel initially
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "Login");

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Dashboard");
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Sign Up");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Login");
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Edit Profile");
            }
        });

        viewLanguagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Languages");
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Game");
            }
        });

        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add friend logic here
            }
        });

        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove friend logic here
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save changes logic here
            }
        });

        startLanguageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start language logic here
            }
        });

        chooseTopicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Choose topic logic here
            }
        });

        startLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start lesson logic here
            }
        });

        endLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // End lesson logic here
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LanguageLearningSystemUI().setVisible(true);
            }
        });
    }
}
