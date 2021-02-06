/**
 * Maze.java
 * 
 * Reads and creates the maze
 * 
 * Programmer: Josue Ballona
 * ZID: Z1832823
 * CSCI 470
 * Due: March 29, 2019
 **/
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
	// initialize variables for the max rows and columns, and also 
	// for the start and the end of the maze
	int startRow, startColumn, endRow, endColumn, maxr, maxc;
	MazeSquare arr [][]; // create array of mazesquares
	
	/**
	 * getRowNum
	 * 
	 * Parameters: none
	 * 
	 * returns: the number of rows
	 */
	public int getRowNum() {
		return endRow;
	}
	
	/*
	 * getColNum
	 * 
	 * Parameters: none
	 * 
	 * returns: the number of columns
	 */
	public int getColNum() {
		return endColumn;
	}
	
	/*
	 * readMaze
	 * 
	 * Parameters: the file to be converted into a maze
	 * 
	 * returns: void
	 * 
	 * Reads the file, sets the max number of rows and columns,
	 * then creates the 2d array for the squares. It also initializes 
	 * each square to the right type
	 */
	public void readMaze(File inputFile) throws FileNotFoundException {
		try {
			//initialize counters
			int rcount = 0;
			int ccount = 0;
			
			// create scanner
			Scanner sc = new Scanner(inputFile);
			// set max number of rows and columns
			maxr = sc.nextInt();
			maxc = sc.nextInt();
			
			// create new maze square object
			MazeSquare ms = new MazeSquare();
			// intialize the arr to an array of maxrows
			arr = new MazeSquare[maxr][];
			// initialize the second array to an arary of maxcolumns
			for (int i = 0; i < maxr; i++)
				arr[i] = new MazeSquare[maxc];
			
			// fetch next line
			sc.nextLine();
			
			// loop while the file has a next line
			// breaks each line into characters and sets the squares.
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String [] linearr = line.split("");
				
				for (int i = 0; i < linearr.length; i++) {
					if (linearr[i].equals("#")) {
						ms.MazeSquare(rcount,ccount, MazeSquare.SquareType.WALL);
						arr[rcount][i] = ms;
						ccount++;
					} else if (linearr[i].equals(".")) {
						ms.MazeSquare(rcount,ccount, MazeSquare.SquareType.SPACE);
						arr[rcount][i] = ms;
						ccount++;
					} else if (linearr[i].equals("s")) {
						ms.MazeSquare(rcount,ccount, MazeSquare.SquareType.SPACE);
						arr[rcount][i] = ms;
						startRow = rcount;
						startColumn = ccount;
						ccount++;
					} else if (linearr[i].equals("e")) {
						ms.MazeSquare(rcount,ccount, MazeSquare.SquareType.SPACE);
						arr[rcount][i] = ms;
						endRow = rcount;
						endColumn = ccount;
						ccount++;
					}
					// create new mazesquare for next loop
					ms = new MazeSquare();
				}
				// set column count to 0 add one to row
				ccount = 0;
				rcount++;
			}
			sc.close();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Input file is not in the correct format.");
		} catch (NoSuchElementException nse) {
			JOptionPane.showMessageDialog(null, "Input file is not in the correct format.");
		} 
	}
	
	/*
	 * clearMazePath
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * calls clearSquare for every square in the array
	 */
	public void clearMazePath() {
		for (int i = maxr - 1; i >= 0; i--) {
			for (int j = maxc - 1; j >= 0; j--)
				arr[i][j].clearSquare();
		}
	}
	
	/*
	 * drawMaze
	 * 
	 * Parameters: a graphics object, and the upper left x and y coordinates of the maze
	 * 
	 * returns: void
	 * 
	 * Draws the rectangle of the whole maze and then draws each individual square inside.
	 */
	public void drawMaze(Graphics g, int startX, int startY) {
		// hold the x coordinate
		int x = startX;
		
		g.drawRect(startX, startY, maxc*MazeSquare.DIMENSIONS, maxr*MazeSquare.DIMENSIONS);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(startX, startY, maxc*MazeSquare.DIMENSIONS, maxr*MazeSquare.DIMENSIONS);
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < maxr; i++) {
			for (int j = 0; j < maxc; j++) {
				arr[i][j].drawSquare(g, startX, startY);
				startX = startX + MazeSquare.DIMENSIONS;
			}
			// add to the y coordinate
			startY = startY + MazeSquare.DIMENSIONS;
			startX = x;	 // go back to the original x coordinate
		}
	}
	
	/*
	 * solveMaze
	 * 
	 * Paramters: none
	 * 
	 * Returns: True if it solves maze
	 * 
	 * non recursive public version that calls the recursive private version
	 */
	public boolean solveMaze() {
		return solveMaze(startRow, startColumn);
	}
	
	/*
	 * solveMaze
	 * 
	 * Parameters: the row and column of the square
	 * 
	 * Returns: true if it sets the square to the path
	 * 
	 * visits each square and decides if it can solve it
	 */
	private boolean solveMaze(int row, int column) {
		// if we're at the xit
		if (row == endRow && column == endColumn) {
			arr[row][column].setToPath();
			return true;
		} 
		
		// if we're at a wall or previously visited square
		if (arr[row][column].isWall() || arr[row][column].getVisited()) {
			return false;
		}
		
		arr[row][column].markVisited(); // mark as visited
		
		// if row is not the first
		if (row != 0) {
			if (solveMaze(row - 1, column)) {
				arr[row][column].setToPath();
				return true;
			}
		}
		
		// if row is not the max row
		if (row != maxr - 1) {
			if (solveMaze(row + 1, column)) {
				arr[row][column].setToPath();
				return true;
			}
		}
		
		// if column is not the first
		if (column != 0) {
			if (solveMaze(row , column - 1)) {
				arr[row][column].setToPath();
				return true;
			}
		}
		
		// if column is not the last column
		if (column != maxc - 1) {
			if (solveMaze(row, column + 1)) {
				arr[row][column].setToPath();
				return true;
			}
		}
		
		return false;
	}
}
