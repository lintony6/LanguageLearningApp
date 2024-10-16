/**
 * This class sets up the board for the board game the user plays 
 * @author Michael Carson 
 */
public class Board {
    private int rows;
    private int columns;
<<<<<<< HEAD:programfiles/src/Board.java
    private int [][] grid;

    
    public Board(int rows, int columns){
        this.rows = rows; 
        this.columns = columns;
        this.grid = new int [rows][columns];
        return ;
=======
    private String[][] grid;

    // creates a board of the size 15 x 15 
    public Board(){
        this(15, 15);
    }
    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.grid = new String[rows][columns];
        initilizeBoard();
    }
    private void initilizeBoard(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j] = "_";
            }
        }
    }
    public void printBoard(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
        System.out.println(grid[i][j] +"");
    }
}
>>>>>>> michael:Board.java
    }
    public boolean setup(){
        return true;
    }
    public int getSize(){
        return rows * columns;
    }
}
