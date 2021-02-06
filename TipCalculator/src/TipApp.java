import java.util.Scanner;

/**
 * TipApp.java
 * 
 * Creates the user interface of the tip calculator
 * 
 * Programmer: Josue Ballona
 * ZID: z1832823
 * CSCI 470
 * Due: February 18, 2019
 **/
public class TipApp {
	// declareing some variables
	double num1; // double to store the bill 
	int num2; // int to store the tip percentage and party size
	boolean flip = false; // boolean for loops when user enters invalid data
	String choice; // string for when user decides to do another bill 
	static boolean end = false; // boolean to stop 
	TipCalculator tc = new TipCalculator(); // tip calculator object
	
	/** 
	 * main
	 * 
	 * Parameters: array of strings
	 * 
	 * Returns: nothing
	 * 
	 * Prints out a header and then loops through the calculate tips method 
	 * until user decides to end.
	 **/
	public static void main(String[] args) {
		TipApp ta = new TipApp(); // "pick yourself up by your bootstraps", create tippapp object
		System.out.println("***  Tip Calculator  ***\n"); // print header
		/*
		 * loops through calculate tips until user decides to end
		 */
		while (!end) {
			ta.calculateTips();
		}
		System.out.println("\nGoodbye!"); // print out a goodbye message
	}
	
	/**
	 * calculateTips
	 * 
	 * Parameters: none
	 * 
	 * Returns: nothing
	 * 
	 * Accepts user input for bill amount, party size, and tip percentage and validates it
	 * then stores it in the tip calculator object and calls the tip calculator methods 
	 * to output bill total and share of bill per party member
	 **/
	public void calculateTips() {		
		Scanner sc = new Scanner(System.in); // create new scanner object
		
		/*
		 * loop to enter bill amount and validate it
		 */
		while(!flip) {
			System.out.print("Enter the bill amount: ");
			if (sc.hasNextDouble()) {
				num1 = sc.nextDouble();
				
				if (num1 <= 0) {
					System.out.println("Enter a valid bill amount.\n");
				} else {
					flip = true;
				}
			} else {
				sc.next();
				System.out.println("Enter a valid bill amount.\n");
			}
		}
		
		tc.setBillAmount(num1); //sets bill amount to num1	
		flip = false; // bring flip back to false
		
		/*
		 * loop to enter tip percentage and validate it
		 */
		while(!flip) {
			System.out.print("Enter the tip percentage as a whole number: ");
			if (sc.hasNextInt()) {
				num2 = sc.nextInt();
				
				if (num2 < 0) {
					System.out.println("Enter a valid tip percentage.\n");
				} else {
					flip = true;
				}
			} else {
				sc.next();
				System.out.println("Enter a valid tip percentage.\n");
			}
		}
		
		tc.setTipPerc(num2); //sets tip percentage		
		flip = false; //sets flip back to false
		
		/*
		 * loop to enter party size and validate it
		 */
		while(!flip) {
			System.out.print("Enter the party size: ");
			if (sc.hasNextInt()) {
				num2 = sc.nextInt();
				
				if (num2 <= 0) {
					System.out.println("Enter a valid party size.\n");
				} else {
					flip = true;
				}
			} else {
				sc.next();
				System.out.println("Enter a valid party size.\n");
			}		
		}
		
		tc.setPartySize(num2); //sets party size
		flip = false; //sets flip back to false
		
		/* 
		 * print output of the bill, tip, party size, total bill, and shares
		 */
		System.out.println("\n***  Your Bill  ***");
		System.out.print("\nBill Amount: $");
		System.out.printf("%.2f", tc.getBillAmount());
		System.out.println();
		System.out.print("Tip Percentage: " + tc.getTipPerc() + "%\n");
		System.out.print("Party Size: " + tc.getPartySize() +"\n\n");
		
		System.out.print("Total Bill (with tip): $");
		System.out.printf("%.2f",  tc.getTotalBill());
		System.out.println();
		System.out.print("Share for Each Individual: $");
		System.out.printf("%.2f", tc.getIndividualShare());
		System.out.println();
		
		/*
		 * loop to do another bill or end, validate choice.
		 */
		while (!flip) {
			System.out.print("\nAnother bill? (y/n): ");
			if (sc.hasNext()) {
				choice = sc.next();
				if (!choice.contentEquals("y") && !choice.contentEquals("Y") && !choice.contentEquals("n") && !choice.contentEquals("N")) {
					System.out.println("\nPlease enter either y or n.\n");
				} else if (choice.contentEquals("y") || choice.contentEquals("Y")) {
					flip = true;
					System.out.println();
				} else {
					flip = true;
					end = true;
					sc.close(); // close scanner item.
				}
			}
		}
		
		flip = false; // make flip false in case of yes.
	}
}
