/**
 * MazeSquare.java
 * 
 * 
 * 
 * Programmer: Josue Ballona
 * ZID: Z1832823
 * CSCI 470
 * Due: March 29, 2019
 **/
import java.awt.*;

public class MazeSquare {
	
	// create enum types for the squares
	public enum SquareType {
		WALL, SPACE, PATH;
	}
	
	// intiailize row, column, type, visit, and DIMENSIONS
	int row, column;
	SquareType type;
	boolean visit = false;
	public static final int DIMENSIONS = 15;
	
	/*
	 * MazeSquare
	 * 
	 * Parameters: row, column, and square type
	 * 
	 * Returns: void
	 * 
	 * sets the row, column, and square type
	 */
	public void MazeSquare(int r, int c, SquareType t) {
		row = r;
		column = c;
		type = t;
	}
	
	/*
	 * clearSquare
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * sets visit to false and changes path type to space
	 */
	public void clearSquare() {
		visit = false;
		if (type == SquareType.PATH)
			type = SquareType.SPACE;
	}
	
	/*
	 * markVisited
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * sets visit to true
	 */
	public void markVisited() {
		visit = true;
	}
	
	/*
	 * getVisited
	 * 
	 * Parameters: none
	 * 
	 * Returns the visit variable
	 */
	public boolean getVisited() {
		return visit;
	}
	
	/*
	 * isWall
	 * 
	 * Parameters: none
	 * 
	 * Returns: true if the square is a wall 
	 */
	public boolean isWall() {
		if (type == SquareType.WALL)
			return true;
		return false;
	}
	
	/*
	 * setToPath
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * sets type to path
	 */
	public void setToPath() {
		type = SquareType.PATH;
	}
	
	/* 
	 * drawSquare
	 * 
	 * Parameters: graphics object, the x and y coordinates of the square 
	 * 
	 * Return: void
	 * 
	 * draws the square based on its type
	 */
	public void drawSquare (Graphics g, int startX, int startY) {
		if (type == SquareType.WALL) {
			g.setColor(Color.BLACK);
			g.drawRect(startX + 1, startY + 1, DIMENSIONS, DIMENSIONS);
			g.fillRect(startX + 1, startY + 1, DIMENSIONS-1, DIMENSIONS-1); 
		}
		if (type == SquareType.SPACE) {
			g.setColor(Color.BLACK);
			g.drawRect(startX + 1,  startY + 1, DIMENSIONS, DIMENSIONS);
			g.setColor(Color.WHITE);
			g.fillRect(startX + 2,  startY + 2,  DIMENSIONS - 1,  DIMENSIONS - 1);
		}
		if (type == SquareType.PATH) {
			g.setColor(Color.BLACK);
			g.drawRect(startX + 1, startY + 1, DIMENSIONS, DIMENSIONS);
			g.setColor(Color.RED);
			g.fillRect(startX + 2, startY + 2, DIMENSIONS - 1, DIMENSIONS - 1);
		}
	}
}
