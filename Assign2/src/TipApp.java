import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.DecimalFormat;


/**
 * TippApp.java
 * 
 * Creates GUI interface of the tip calculator
 * 
 * Programmer: Josue Ballona
 * ZID: Z1832823
 * CSCI 470
 * Due: March 1, 2019
 */
public class TipApp extends JFrame implements ActionListener, ChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	// declare swing componnts
	private JButton calcButton = new JButton("Calculate");
	private JButton clearButton = new JButton("Clear");
	private JTextField billField = new JTextField(10);
	private JSlider tipSlider = new JSlider(0, 50, 20);
	private JLabel billTot = new JLabel("Total Bill (with Tip)");
	private JLabel share = new JLabel("Individual Share");
	TipCalculator tc = new TipCalculator();
	SpinnerModel model = new SpinnerNumberModel(1, 1, 500, 1);
	private JSpinner sizeSpinner = new JSpinner(model);
	private JTextArea billTotArea = new JTextArea();
	private JTextArea sharesArea = new JTextArea();
	
	//declare variables to store data
	int spinnum;
	double finalBill, shares;
	
	/*
	 * main
	 * 
	 * Parameters: array of strings
	 * 
	 * Returns: nothing
	 * 
	 * creates the GUI using EDT
	 */
	public static void main (String [] args) {
		EventQueue.invokeLater(() -> {
			TipApp frame = new TipApp("Tip Calculator");
			frame.createAndShowGUI();
		});
	}
	
	/*
	 * TipApp
	 * 
	 * Parameters: String representing title of window
	 * 
	 * returns nothing
	 * 
	 * Gives the GUI window a title
	 */
	private TipApp(String title) {
		super(title);
	}
	
	/*
	 * createAndShowGUI
	 * 
	 * Parameters: none
	 * 
	 * Returns: nothing
	 * 
	 * Creates the GUI components and displays them.
	 */
	private void createAndShowGUI() {
		//calls initComponents to create other GUI items
		initComponents();
		
		// adds action listeners to buttons
		calcButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		// sets close operation
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/*
	 * initComponents
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 * 
	 * creates components of GUI 
	 */
	private void initComponents() {
		// creates grid layout and sets border
		JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// adds jlabel and jtextfield for bill amount onto panel
		panel.add(new JLabel("Bill amount"));
		panel.add(billField);
		
		// adds jlabel and slider for tip percentage onto panel
		panel.add(new JLabel("Tip percentage"));
		panel.add(tipSlider);
		tipSlider.setMajorTickSpacing(10);
		tipSlider.setMinorTickSpacing(5);
		tipSlider.setPaintTicks(true);
		tipSlider.setPaintLabels(true);
		tipSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tc.setTipPerc(tipSlider.getValue());
			}
		});
		
		// adds jlabel and spinner for party size onto panel
		panel.add(new JLabel("Party size"));
		panel.add(sizeSpinner);
		sizeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tc.setPartySize((int)sizeSpinner.getValue());
			}
		});
		
		// adds button to panels
		panel.add(calcButton);
		panel.add(clearButton);
		
		add(panel, BorderLayout.CENTER);
		
		// adds bill total label and text area to panel
		// as well as share label and text area
		panel.add(billTot);
		panel.add(billTotArea);
		billTotArea.setText("$");
		billTotArea.setEditable(false);;
		panel.add(share);
		panel.add(sharesArea);
		sharesArea.setText("$");
		sharesArea.setEditable(false);

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
		double num1;
		DecimalFormat df = new DecimalFormat("0.00");
		
		if (e.getSource() == clearButton) {
			billField.setText("");
			tc.setBillAmount(0);
			billTotArea.setText("$");
			sharesArea.setText("$");
			tipSlider.setValue(20);
			tc.setTipPerc(20);
			sizeSpinner.setValue(1);
			tc.setPartySize(1);
		} else {
			try {
				num1 = Double.parseDouble(billField.getText());
				if (num1 <= 0)
					JOptionPane.showMessageDialog(null, "Bill amount must be valid.");
				else {
					tc.setBillAmount(num1);
					finalBill = tc.getTotalBill();
					shares = tc.getIndividualShare();
					billTotArea.setText("$" + df.format(finalBill));
					sharesArea.setText("$" + df.format(shares));
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Bill amount must be numeric.");
			}	
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
