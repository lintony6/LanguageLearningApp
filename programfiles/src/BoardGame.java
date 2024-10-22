import java.util.ArrayList;
import java.util.Scanner;

import LanguageLearningApp.programfiles.src.Board;


/**
 * this class has all the functionality and implementation of the board game 
 * @author Michael Carson 
 */
public class BoardGame {
    private static BoardGame instance;
    private Board boardgame;
    private Player player;
    private User user;
    private ArrayList<Lesson> lessons;
    private int currentLesson;
    private Progress progress;
    
    private BoardGame(){
       this.boardgame = new Board();
        this.user = new User("username", "password");
        this.player = new Player(user.getUsername());
        this.lessons = new ArrayList<>();
        this.currentLesson = 0;
        this.progress = new Progress();
    }
    public static BoardGame getInstance(){
        if (instance == null){
            instance = new BoardGame();
            }
            return instance;
    }
    public static void loadGame(int languageProgress){
        return 0;
        
    }
    /**
     * prints the board and user location on the board
     * asks for user input to begin game 
     */
    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        boardgame.updateBoard(player);
        boardgame.printBoard();
        while (true) {
            System.out.println("Press 1 to start a lesson or 0 to quit");
            int input = scanner.nextInt();
            if (input == 0){
                endGame();
            }
            if (input == 1){
                boolean completeLesson = startLesson();
            }
    }
    /**
     * checks if there is a lesson left 
     * gets a lesson if there is one to get 
     * asks for the user input to begin lesson 
     * if the user completes the lesson it adds progress and advances to the next space 
     * @return
     */
    private boolean startLesson(){
        if (currentLesson >= lessons.size()) {
            System.out.println("All lessons completed!");
            return true;
        }

        Lesson lesson = lessons.get(currentLesson);
        lesson.startLesson();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Begin the lesson by pressing 1 (or any other key to fail): ");
        int input = scanner.nextInt();

        if (input == 1) {
            lesson.startLesson();
        if (completeLesson() == true){
            progress.updateProgress(lesson, 2.25); //15*15/100 = 2.25 per lesson  
            currentLesson++;
            System.out.println("Lesson Complete!!");

            System.out.println("Press 1 to move forward");
            int steps = scanner.nextInt();
            nextSpace(steps);
            return true;
        }
        } else {
            System.out.println("Lesson not completed. Try again.");
            return false;
        }
    }
}
    public static void endGame(){
        return null;
    }
   /**
    * checks if the user completes the lesson 
    * moves the player forward if the lesson is completed 
    * keeps the player at the same space and gives a message if the lesson is not completed 
    * @param steps
    */
    public void nextSpace(int steps){
        boolean completeLesson = startLesson();
        if(completeLesson){
            player.move(steps, 15 * 15);
            boardgame.updateBoard(player);
        }else{
            System.out.println("Please complete the lesson successfully in order to move forward!");
        }
    }
    public static void saveBoard(){
        return 0;
    }
    }
    
