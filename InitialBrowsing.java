import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the front-end where the user makes searches, view cart. 
 * @author Nami Kim
 */
public class InitialBrowsing {

	private String fileName = "";
	private ArrayList<String[]> searchResult = new ArrayList<>();
	private Cart userCart = new Cart();
	private Display display = new Display();

	/**
	 * Prompt user to enter name/year of movie and perform a search.
	 * Calls ParseSearchResult to process the search result, calls display function to 
	 * display the search result and calls addToCart method to ask user whether to add an item to cart.
	 */
	public void userSearch() {

		boolean loop = true;

		while(loop) {
			searchResult.clear();
			System.out.print("Enter title of the movie to be searched (r to return to previous menu:main) >>> ");
			Scanner in = new Scanner(System.in);
			SearchMovies keyWord;

			String userInput = in.nextLine();
			if (userInput.toLowerCase().equals("r")) {
				return; // return to previous menu if user enters r 
			} else {
				// user input makes Search URL 
				keyWord = new SearchMovies(userInput);
				fileName = userInput.replaceAll(" ", "")+".csv";
			}
		
			System.out.print("Enter year of release or press enter to search without a year (r to return to previous menu:main) >>> ");
			userInput = in.nextLine();
			
			if (userInput.toLowerCase().equals("r")) {
				return; // return to previous menu if user enters r 
			} else {

				try{
					keyWord.setYear(Integer.parseInt(userInput));
				} catch(NumberFormatException e) {
					//System.out.println("No year value recognized. Continuing search with title only.");
				}
			}

			// set year if needed
			String url = keyWord.constructSearch();
			MovieApiConnection c = new MovieApiConnection(url); 
			String cs = c.requestGET();
			DataParser dpc = new DataParser(cs);


			if(dpc.processClob().size() <= 1){
				display.noSearchResult();
			} else { //when (dpc.processClob().size() > 1)
				// if we got something back, then print to csv
				FileHandler.MovieSearchedWriter(fileName, dpc.processClob());
				parseSearchResult();
				display.searchResult(searchResult);
				addToCart();
			}
		}
	}

	/**
	 * Parse the search result retrieved in a CSV file,
	 * store the result in instance variable searchResult.
	 */
	public void parseSearchResult() {

		File f = new File(fileName);

		try {
			Scanner in = new Scanner(f);
			while (in.hasNextLine()) {
				String[] line = in.nextLine().split(",");
				String[] lineWithOutQuotationMark = new String[5];

				// remove double quotation mark 
				// + take only first 45 letters of movie title 
				if (line[1].length() > 45) {
					lineWithOutQuotationMark[1] = line[1].substring(1, line[1].length() - 1).substring(0, 45);
				} else {
					lineWithOutQuotationMark[1] = line[1].substring(1, line[1].length() - 1);
				}
				for (int i = 2; i < line.length; i++) {
					lineWithOutQuotationMark[i] = line[i].substring(1, line[i].length() - 1);
				}
				lineWithOutQuotationMark[0] = line[0];
				searchResult.add(lineWithOutQuotationMark);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/** 
	 * Ask user if want to add an item to cart, process the request. 
	 */
	public void addToCart() {
		int addToCartNumber;

		Scanner in = new Scanner(System.in);

		boolean loop = true;

		while(loop) {
			System.out.print("Enter number of the movie you want to add to cart, c to view your cart, r to return to previous menu:search >>> ");

			String input = in.nextLine();

			if (input.toLowerCase().equals("r")) {
				return;
			}
			else if (input.toLowerCase().equals("c")) {
				loop = false;
				viewCart();
			}
			else {
				try {
					addToCartNumber = Integer.parseInt(input);

					if (addToCartNumber < 1 || addToCartNumber >= searchResult.size()) {
						System.out.println("Invalid input");
					} 
					else {
						String[] userChoiceMovie = searchResult.get(addToCartNumber);

						if (userCart.isAlreadyInCart(userChoiceMovie)) {
							System.out.println("Item already in cart");
						}
						else {
							String title = userChoiceMovie[1];// title of movie to be added to cart 
							userCart.addToCart(userChoiceMovie);
							System.out.println("Movie number " + addToCartNumber + ", " + title + " added to cart.");
						}
					}
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
		}
	}

	/**
	 * Show cart by calling display function in cart class.
	 * User can also remove an item from the cart, this method processes the request. 
	 */
	public void viewCart() {

		boolean loop = true;
		while(loop) {
			display.cart(userCart);
			System.out.print("Enter c to checkout, number of title to remove item from cart, r to return to previous menu >>> ");
			Scanner in = new Scanner(System.in);
			String userInput = in.nextLine();

			try { // if user enters a number, remove the item number from cart 
				int removeFromCartNumber = Integer.parseInt(userInput);

				if (removeFromCartNumber < 1 || removeFromCartNumber > userCart.getCartSize()) {
					System.out.println("Invalid input");
				} 
				else {
					userCart.removeFromCart(removeFromCartNumber);
				}
			} catch(NumberFormatException e) {

				if (userInput.toLowerCase().equals("c")) {
					loop = false;
					Checkout checkout = new Checkout();
					checkout.runCheckoutProcess(userCart);
				}
				else if (userInput.toLowerCase().equals("r")) {
					return;
				}
				else {
					System.out.println("Invalid input");
				}
			}

		}

	}

	/**
	 * Present main menu to the user, if user's input is valid, 
	 * return the character representation of user's choice of action. 
	 * @return
	 */
	public Character inputHandling() {

		Character returnChar = Character.MIN_VALUE;
		boolean loop = true; 

		while (loop) {
			System.out.print("Enter s to search for a movie, c to view cart, q to quit >>> ");
			Scanner in = new Scanner(System.in);

			String userInput = in.nextLine().toLowerCase();
			if (userInput.equals("s") || userInput.equals("c") || userInput.equals("q")) {
				returnChar = userInput.charAt(0);
				loop = false; 
			}
			else {
				System.out.println("Invalid input");
			}
		}

		return returnChar;
	}

	/**
	 * Run initial browsing class via a loop that repeats prompt and input handling.
	 */
	public void run() {
		display.welcomeUser();
		boolean loop = true;		
		while (loop) {
			Character userInput = inputHandling();

			switch (userInput) {
			case 's':
				userSearch();
				break;
			case 'c':
				viewCart();
				break;
			case 'q':
				loop = false;
				break;
			}
		}
		display.exit();
	}
}
