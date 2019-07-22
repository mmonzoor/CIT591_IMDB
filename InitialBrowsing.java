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
					
		for (String genreName : genreArr) {
			
			System.out.printf("%-20s", genreName);
			count++;
			
			if (count == genrePerLine) {
				System.out.println();
				count = 0;
			}
		}
	}
	
	/** 
	 * print out browsing menu. 
	 */
	public void displayBrowsingOptions() {
		
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
	}
	
	public static void main(String[] args) {
		
		InitialBrowsing test = new InitialBrowsing();
		test.run();
	}
}
