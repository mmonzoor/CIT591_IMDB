import java.util.ArrayList;
import java.util.Scanner;

/**
 * Display browsing menus 
 * @author Nami Kim
 *
 */
public class InitialBrowsing {
	private String storeTitle = "JD-NK-MM Movie Rental";
	
	/**
	 * Display welcome message.
	 */
	public void welcomeUser() {
		System.out.println("Welcome to " + storeTitle);
		System.out.println();
	}
	
	/**
	 * Display popular movies.  
	 */
	public void displayPopularMovies() {
		System.out.println("Most Popular");
		displayHorizontalBar();
		
		int numOfMoviesToShow = 3;
		
		for (int i = 0; i < numOfMoviesToShow; i++) {
			// retrieve from popular movie set and display titles on screen 
		}
		System.out.printf("%-20s %-20s %-20s\n", "osne", "two", "thredsfe"); // dummy values
		System.out.println();
	}
	
	/**
	 * Display new movies. 
	 */
	public void displayNewMovies() {
		System.out.println("New");
		displayHorizontalBar();
		
		int numOfMoviesToShow = 3;
		
		for (int i = 0; i < numOfMoviesToShow; i++) {
			// retrieve from popular movie set and display titles on screen 
		}
		System.out.printf("%-20s %-20s %-20s\n", "osne", "two", "thredsfe"); // dummy values
		System.out.println();
	}
	
	/**
	 * Display a horizontal bar on screen.
	 */
	public void displayHorizontalBar() {
		int horizontalSpaceSize = 60;
		
		for (int i = 0; i < horizontalSpaceSize ; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	/**
	 * Display movie genres on screen.
	 */
	public void displayGenre() {
		System.out.println("Search by Genre");
		displayHorizontalBar();
		
		MovieGenre genre = new MovieGenre();
		String[] genreArr = genre.getGenre();
		
		int genrePerLine = 3; // show 3 genre names per line 
		int count = 0;
					
		for (int i = 0; i < genreArr.length; i++) {
			
			System.out.printf("%d. %-20s", i + 1, genreArr[i]);
			count++;
			
			if (count == genrePerLine) {
				System.out.println();
				count = 0;
			}
		}
		System.out.println();
		System.out.println();
	}
	
	/** 
	 * Prompt to start movie search by name or genre code 
	 */
	public void promptUserSearch() {
		
		Scanner in = new Scanner(System.in);
		
		boolean loop = true;
		while (loop) {
			System.out.print("Please type in name of movie or genre number\n[C to checkout | Q to quit] : ");
			
			if (in.nextLine().toUpperCase().equals("Q")) {
				loop = false;
				displayExit();
			}
			
			if (in.nextLine().toUpperCase().equals("C")) {
				loop = false;
				// go to check out 
			}
		}
		
		
	}
	
	public void displayExit() {
		System.out.println("Thank you. Have a nice day");
	}
	
	/**
	 * Process user input, protect against erroneous inputs.
	 */
	public void inputHandling() {
		
	}
	
	public void run() {
		welcomeUser();
		displayPopularMovies();
		displayNewMovies();
		displayGenre();
		promptUserSearch();
	}
	
	public static void main(String[] args) {
		
		InitialBrowsing test = new InitialBrowsing();
		test.run();
	}
}
