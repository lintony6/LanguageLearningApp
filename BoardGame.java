
/**
 * this class has all the functionality and implementation of the board game 
 * @author Michael Carson 
 */

public class BoardGame {
private static BoardGame instance;
private Board boardgame;
private Player player;
private User user;

private BoardGame(){
    this.boardgame = new Board();
    this.player = new Player(user);
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
public static void startGame(){
    return 0;
}
public static void endGame(){
    return null;
}
public static void nextSpace(){
    player.move(steps, boardgame.getColumns() * boardgame.getRows());
    boardgame.updateBoard(player);
    startLesson();
    return null;
}
public static void saveBoard(){
    return 0;
}
}
