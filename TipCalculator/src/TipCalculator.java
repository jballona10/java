/**
 * TipCalculator.java
 * 
 * Calculates the tip for a bill
 * 
 * Programmer: Josue Ballona
 * ZID: Z1832823
 * CSCI 470
 * Due: February 18, 2019.
 **/

public class TipCalculator {
	
	// declare variables
	private double billAmount = 0.00;
	private int tipPerc = 20;
	private int partySize = 1;
	private double totalBill = 0;
	private double share = 0;
	
	/**
	 * getBillAmount
	 * 
	 * parameters: none
	 * 
	 * returns: a double representing the bill amount
	 **/
	public double getBillAmount() {
		return billAmount;
	}
	
	/**
	 * setBillAmount
	 * 
	 * parameters: a) double representing the bill amount
	 * 
	 * returns: nothing
	 **/
	public void setBillAmount(double a) {
		billAmount = a;
	}
	
	/**
	 * getTipPerc
	 * 
	 * parameters: none
	 * 
	 * returns: an integer returning the tip percentage
	 **/
	public int getTipPerc() {
		return tipPerc;
	}
	
	/**
	 * setTipPerc
	 * 
	 * parameters: b) an integer representing tip percentage
	 * 
	 * returns: nothing
	 **/
	public void setTipPerc(int b) {
		tipPerc = b;
	}
	
	/**
	 * getPartySize
	 * 
	 * parameters: none
	 * 
	 * returns: an integer representing party size
	 **/
	public int getPartySize() {
		return partySize;
	}
	
	/**
	 * setPartySize
	 * 
	 * Parameters: c) an integer representing the party size
	 * 
	 * returns: nothing
	 **/
	public void setPartySize(int c) {
		partySize = c;
	}
	
	/**
	 * getTotalBill
	 * 
	 * parameters: none
	 * 
	 * returns: a double representing the bill including the tip
	 **/
	public double getTotalBill() {
		totalBill = billAmount + ((billAmount * tipPerc) / 100);
		return totalBill;
	}
	
	/**
	 * getIndividualShare
	 * 
	 * parameters: none
	 * 
	 * returns: a double representing each person's share
	 **/
	public double getIndividualShare() {
		share = totalBill / partySize;
		return share;
	}
}

