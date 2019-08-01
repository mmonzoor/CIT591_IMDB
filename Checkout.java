import java.util.Scanner;

/**
 * An interactive interface to checkout movies and calculate bills. Provide the
 * user with several choices to abandon the checkout process or continue to
 * browse if he or she changes decisions along the way.
 * 
 */
public class Checkout {

	String customerName;
	double bill;
	String creditCard;

	/**
	 * Constructor for a Checkout object; initialize variables
	 * 
	 */
	Checkout() {
		customerName = "";
		bill = 0.0;
		creditCard = "";
	}

	/**
	 * Ask for user decision to checkout, abandon cart, or continue browsing based
	 * on interactive user choices
	 */
	public Cart runCheckoutProcess(Cart shoppingCart) {
		Cart cart = shoppingCart;
		Scanner userInput = new Scanner(System.in);
		String userDecision = "";
		System.out.println("Welcome to checkout! Are you ready to proceed to checkout?");
		userDecision = customerDecision();
		if (userDecision.equals("Yes")) {
			System.out.println("Please tell us your name:");
			customerName = userInput.nextLine();
			double currentBill = calculateBill(cart);
			System.out.println(customerName + ", your total bill amount is " + currentBill);
			creditCard = getCreditCard();
			cart.clearCart();
			System.out.println(
					"Your order is complete. You have been charged " + currentBill + " to your card " + creditCard);
			System.out.println("Thank you, " + customerName + "! Please visit us again in the future.");
		} else {
			System.out.println("Would you like to keep browsing?");
			userDecision = customerDecision();
			if (userDecision.equals("Yes")) {
				System.out.println("You can continue to browse for movies. Enjoy!");
			} else {
				cart.clearCart();
				System.out.println(
						"Your cart is clear. You are about to leave the store. Are you sure you want to leave?");
				userDecision = customerDecision();
				if (userDecision.equals("Yes")) {
					System.out.println("Thank you for visiting us. We hope to see you again. Goodbye.");
				} else {
					System.out.println("You can continue to browse for movies. Enjoy!");
				}
			}
		}
		userInput.close();
		return cart;
	}

	/**
	 * Calculate bill for the movie rental shopping cart
	 * 
	 * @return
	 */
	public double calculateBill(Cart shoppingCart) {
		for (String[] strings : shoppingCart.getCart()) {
			try {
				bill += Double.parseDouble(strings[4]); // price is the 5th element in a Movie object as an array
			} catch (NumberFormatException nfe) {
				System.out.println("Price not available for " + strings[1]);
			}
		}
		return bill;
	}

	/**
	 * Ask for customer credit card information. The method checks if the input is a
	 * 16-digit number, and prompts for credit card information again if user input
	 * is invalid.
	 * 
	 * @return
	 */
	public String getCreditCard() {
		String creditCard = "";
		Scanner creditCardScanner = new Scanner(System.in);
		boolean cardError = true;
		while (cardError) {
			System.out.println("Please enter your credit card number:");
			creditCard = creditCardScanner.nextLine();
			if (creditCard.length() == 16) {
				try {
					Integer.parseInt(creditCard);
					cardError = false;
				} catch (NumberFormatException nfe) {
					System.out.println("Invalid credit card. Please enter a 16-digit number.");
				}
			}
		}
		creditCardScanner.close();
		return creditCard;
	}

	/**
	 * Ask for user decision, "Yes" or "No", and perform error checking for invalid
	 * inputs
	 * 
	 * @return
	 */
	public String customerDecision() {
		String decision = "";
		Scanner userDecisionScan = new Scanner(System.in);
		String userChoice = "";
		boolean errorIndicator = true;

		while (errorIndicator) {
			System.out.println("Please enter yes or no for your choice.");
			userChoice = userDecisionScan.nextLine();
			if (userChoice.toLowerCase().equals("yes")) {
				decision = "Yes";
				errorIndicator = false;
			} else if (userChoice.toLowerCase().equals("no")) {
				decision = "No";
				errorIndicator = false;
			} else {
				System.out.println("Invalid choice.");
			}
		}

		userDecisionScan.close();
		return decision;
	}

}
