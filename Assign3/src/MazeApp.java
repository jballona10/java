/**
 * MazeApp.java
 * 
 * Creates the maze gui objects
 * 
 * Programmer: Josue Ballona
 * ZID: Z1832823
 * CSCI 470
 * Due: March 29, 2019
 **/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;


public class MazeApp extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	// "lift yourself up by your own bootstraps"
	MazePanel mp = new MazePanel();
	
	// create buttons and file chooser
	private JButton open = new JButton("Open Maze File");
	private JButton solve = new JButton("Solve Maze");
	private JButton clear = new JButton("Clear Maze");
	final JFileChooser fc = new JFileChooser();
	
	
	/*
	 * main
	 * 
	 * Parameter: array of string objects
	 * 
	 * returns: void
	 * 
	 * has the event thread to create the maze
	 */
	public static void main(String [] args) {
		EventQueue.invokeLater(() -> {
			MazeApp frame = new MazeApp("Maze Solver");
			frame.createAndShowGUI();
		});
	}
	
	/*
	 * MazeApp
	 * 
	 * Parameters: title
	 * 
	 * returns: 
	 * 
	 * sets title of window
	 */
	private MazeApp(String title) {
		super(title);
	}
	
	/*
	 * createAndShowGUI() 
	 * 
	 * Parameters: none
	 * 
	 * returns: void
	 * 
	 * 
	 */
	private void createAndShowGUI() {
		// creates gui objects
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		JPanel bpanel = new JPanel(new FlowLayout());
		panel.add(bpanel, BorderLayout.SOUTH);
		panel.add(mp, BorderLayout.CENTER);
		bpanel.add(open, FlowLayout.LEFT);
		bpanel.add(solve, FlowLayout.CENTER);
		bpanel.add(clear, FlowLayout.RIGHT);
		
		// adds action listeners to buttons
		open.addActionListener(this);
		solve.addActionListener(this);
		clear.addActionListener(this);
		
		add(panel, BorderLayout.CENTER);
		
		// sets close operation
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/*
	 * actionPerformed
	 * 
	 * Parameters: an action event representing one of two buttons
	 * 
	 * Returns: void
	 * 
	 * sets functions for cancel and calculate buttons
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		
		// if open button is pressed
		if (e.getSource() == open) {
			int returnval = fc.showOpenDialog(mp);
			
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					mp.readMaze(file);
					solve.setEnabled(true);
					clear.setEnabled(false);
				} catch(FileNotFoundException fnf){
					JOptionPane.showMessageDialog(null, "The file was not found.");
				}
			}
			// else if solve is pressed 
		} else if (e.getSource() == solve) {
			mp.solveMaze();
			solve.setEnabled(false);
			clear.setEnabled(true);
			// else if clear is pressed
		} else {
			mp.clearMazePath();
			solve.setEnabled(true);
			clear.setEnabled(false);
		}
	}
}
