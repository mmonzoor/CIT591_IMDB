import java.util.ArrayList;
import java.util.Scanner;

/**
 * An interactive interface to checkout movies and calculate bills.
 * Provide the user with several choices to abandon the checkout
 * process or continue to browse if he or she changes the decision
 * again.
 * 
 */
public class Checkout{
	
	ArrayList<String> cart;
	String customerName;
	double bill;
	String creditCard;
	
	/**
	 * Constructor for a Checkout object
	 * @param shoppingCart
	 */
	Checkout (ArrayList<String> shoppingCart) {
		cart = shoppingCart;
	}

	/**
	 * Ask for user decision to checkout, abandon cart, or continues browsing
	 * based on interactive user choices
	 */
	public void checkoutProcess() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Welcome to checkout! Are you ready to proceed?");
		
		
		userInput.close();
	}
	
	/**
	 * Calculate bill for the movie rental shopping cart
	 * @return
	 */
	public double calculateBill() {
		// calculate bill amount
		return bill;
	}
	
	/**
	 * Clear shopping cart and offer the user one more option 
	 * to restart browsing	
	 */
	public void clearCart() {
		// set cart values to initial state
	}
		
	/**
	 * Ask for user decision and perform error checking for invalid inputs
	 * @param userChoice
	 * @return
	 */
	public boolean customerDecision(String userChoice) {
		Scanner userChoiceScan = new Scanner(System.in);
		// ask for user decision and check for valid user input
		if (userChoice.equals("Yes")) {
			return true;
		} else {
			return false;
		}
		
	}

}
