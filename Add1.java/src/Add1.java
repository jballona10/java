import java.util.Scanner;

/**
* Add1.java
*
* Console program to add two numbers. 
*/
public class Add1 {
	public static void main(String[] args) {
		String amountStr;
		double num1, num2;
		
		Scanner sc = new Scanner(System.in);
		
		// read the first number as a string
		System.out.println("Enter the first number: ");
		amountStr = sc.next();
		
		// try to convert String to double for math
		try {
			num1 = Double.parseDouble(amountStr);
		} catch (NumberFormatException nfe) {
			System.out.println("1st number invalid.");
			return;
		}
		
		// read second number as a string
		System.out.println("Enter the second number: ");
		amountStr = sc.next();
		
		// try to convert string to double for math
		try {
			num2 = Double.parseDouble(amountStr);
		} catch (NumberFormatException nfe) {
			System.out.println("2nd number invalid.");
			return;
		}
		
		// compute and print sum
		System.out.printf("Sum is: %.2f\n", num1 + num2);
	}
}