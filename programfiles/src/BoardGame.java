
/**
 * this class has all the functionality and implementation of the board game 
 * @author Michael Carson 
 */

public class BoardGame {
    //singleton instance 
private static BoardGame instance;
private Board boardgame;
private User player;

private BoardGame(){
    boardgame = new Board(25, 25);  
}
public static BoardGame getInstance(){
    if(instance == null){
        instance = new BoardGame();
    }
    return instance;
}
public static void loadGame(int languageProgress){
    return 0;
}
public static void startGame(){
    return 0;
}
public static void endGame(){
    return null;
}
public static void nextSpace(){
    return null;
}
public static void saveBoard(){
    return 0;
}
}
