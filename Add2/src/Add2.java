import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Add2
*
* Swing program to find sum of two numbers.
*/
public class Add2 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JButton addButton = new JButton("Add Numbers");
	private JButton clearButton = new JButton("Clear Numbers");
	private JTextField num1Field = new JTextField(10);
	private JTextField num2Field = new JTextField(10);
	private JLabel sumLabel = new JLabel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Add2 frame = new Add2("SUm of Two Numbers");
			frame.createAndShowGUI();
		});
	}
	
	private Add2(String title) {
		super(title);
	}
	
	/** 
	 * Create the GUI and show it. For thread safety, this method should
	 * be invoked from the event dispatch thread.
	 */
	private void createAndShowGUI() {
		
		initComponents();
		
		// Add listeners for the buttons.
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		// display the window
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/**
	 * Set the frame's layout, create UI components, add them to layout
	 */
	private void initComponents() {
		JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.add(new JLabel("First number:"));
		panel.add(num1Field);
		panel.add(new JLabel("Second number:"));
		panel.add(num2Field);
		panel.add(new JLabel("Sum:"));
		panel.add(sumLabel);
		panel.add(addButton);
		panel.add(clearButton);
		
		add(panel, BorderLayout.CENTER);
	}
	
	/*
	 * Handle ActionEvents from buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		double num1, num2;
		
		if (e.getSource() == clearButton) {
			num1Field.setText("");
			num2Field.setText("");
			sumLabel.setText("");
		} else {
			
			// try to convert string to double
			try {
				num1 = Double.parseDouble(num1Field.getText());
			} catch (NumberFormatException nfe) {
				sumLabel.setText("1st number invalid.");
				return;
			}
			
			// try to convert string to double
			try {
				num2 = Double.parseDouble(num2Field.getText());
			} catch (NumberFormatException nfe) {
				sumLabel.setText("2nd number invalid");
				return;
			}
			
			// compute an display sum
			sumLabel.setText(String.format("%.2f",  num1 + num2));
		}
	}
}