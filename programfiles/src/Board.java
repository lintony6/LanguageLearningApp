/**
 * This class sets up the board for the board game the user plays 
 * @author Michael Carson 
 */
public class Board {
    private int rows;
    private int columns;
    private int [][] grid;

    
    public Board(int rows, int columns){
        this.rows = rows; 
        this.columns = columns;
        this.grid = new int [rows][columns];
        return ;
    }
    public boolean setup(){
        return true;
    }
    public int getSize(){
        return rows * columns;
    }
}
