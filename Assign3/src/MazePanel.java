/**
 * MazePanel.java
 * 
 * Creates the panel to draw the square
 * 
 * Programmer: Josue Ballona
 * ZID: Z1832823
 * CSCI 470
 * Due: March 29, 2019
 **/
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;

public class MazePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// create and initialize the maze object and solutionattempted and solutionfound
	Maze maze = null;
	boolean solutionAttempted = false;
	boolean solutionFound = false;
	
	/*
	 * readMaze
	 * 
	 * Parameters: the file to be read
	 * 
	 * Returns: void
	 * 
	 * 
	 */
	public void readMaze(File inputFile) throws FileNotFoundException {
		
		// sets solution attempted and found to false
		solutionAttempted = false;
		solutionFound = false;
		
		maze = new Maze(); // creates the new maze object
		
		maze.readMaze(inputFile); // read the new maze
		repaint(); // redraw
	}
	
	/*
	 * clearMazePath
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * clears the mazepath and sets attempted and found to false
	 */
	public void clearMazePath() {
		solutionAttempted = false;
		solutionFound = false;
		maze.clearMazePath();
		repaint();
	}
	
	/*
	 * solveMaze
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * attempts to solve the maze. sets attempted to true and
	 * sets found to the return of maze.SolveMaze
	 */
	public void solveMaze() {
		solutionAttempted = true;
		solutionFound = maze.solveMaze();
		repaint();
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
	
    /*
     * (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Dimension size = getPreferredSize();
		int appHeight = size.height;
		int appWidth = size.width;
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, appHeight, appWidth);
		g.fillRect(0, 0, appWidth, appHeight);
		g.setColor(Color.BLACK);
		if (maze != null) {
			int mazeHeight = (15 * maze.maxr);
			int mazeWidth = (15 * maze.maxc);
			maze.drawMaze(g, (appWidth - mazeWidth)/2,(appHeight - mazeHeight)/2);
			if (solutionAttempted && !solutionFound) {
				g.setColor(Color.BLACK);
				g.setFont(new Font("default", Font.BOLD, 16));
				g.drawString("No solution exists for this maze!", 130, 480);
			}
			if (solutionAttempted && solutionFound) {
				g.setFont(new Font("default", Font.BOLD, 16));
				g.drawString("Solved!", 230,  480);
			}
		}
	}
}


