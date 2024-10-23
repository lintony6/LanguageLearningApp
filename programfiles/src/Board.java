import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

/**
 * This class sets up the board for the board game the user plays 
 * @author Michael Carson 
 */
public class Board extends JPanel{
    private int rows;
    private int columns;
    private String[][] grid;
    private static final int CELL_SIZE = 30; // pixel size 

    // creates a board of the size 15 x 15 
    public Board(){
        this(15, 15);
    }

    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.grid = new String[rows][columns];
        initilizeBoard();
        setPreferredSize(new Dimension(columns * CELL_SIZE, rows * CELL_SIZE));
    }
    //initializes the board with the default values 
    private void initilizeBoard(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j] = "_";
            }
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.drawString(grid[i][j], j * CELL_SIZE + CELL_SIZE / 2, i * CELL_SIZE + CELL_SIZE / 2);
            }
        }
    }
    //draws the board 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.drawString(grid[i][j], j * CELL_SIZE + CELL_SIZE / 2, i * CELL_SIZE + CELL_SIZE / 2);
            }
        }
    }

    public void updateBoard(Player player) {
        initializeBoard(); // Reset the board
        grid[player.getRow(columns)][player.getCol(columns)] = player.getSymbol();
        repaint(); // Repaint the board to reflect changes
    }
    


    /*prints the board
    public void printBoard(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
        System.out.println(grid[i][j] + " ");
        }
    }
    ]*/

    public boolean setup(){
        return true;
    }
   