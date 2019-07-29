import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the front-end where the user  makes searches. 
 * @author Nami Kim
 */
public class InitialBrowsing {
	private String fileName = "";
	private ArrayList<String[]> searchResult;
	private Cart userCart;
	
	public InitialBrowsing() {
		this.fileName ="";
		this.searchResult = new ArrayList<>();
		this.userCart = new Cart();
	}
	
	/** 
	 * Ask user for search keyword, year, pass on to API, retrieve results in a CSV file. 
	 * returns whether the prompt should loop 
	 */
	public boolean userSearchOld() {
		searchResult.clear();
		fileName = "";
		
		System.out.println("please enter the title of the movie to be searched.");
        Scanner in = new Scanner(System.in);
        
        String searcKeyword = in.nextLine();
        
        if (searcKeyword.toLowerCase().equals("q")) {
        	//in.close();
        	return false;
        } 
        
        // Search URL construction

        SearchMovies keyWord = new SearchMovies(searcKeyword);
		
        // prompt for year 
        // is there a reason for using another scanner?
        System.out.println("would you like to input a year to search with as well?(y/n)");
        
        String choice = in.nextLine().toLowerCase();
        
        if(choice.toLowerCase().equals("q")) {
        	in.close();
        	return false;
        } else if(choice.equals("y") || choice.equals("yes")){
            
        	//Scanner yearInput = new Scanner(System.in);
            System.out.println("What year would you like to search by? ");
            String year = in.nextLine();
            
            if (year.toLowerCase().equals("q")) {
            	//in.close();
            	return false;
            }
            else {
            	try{
            		keyWord.setYear(Integer.parseInt(year));
            	} catch(NumberFormatException e) {
            		System.out.println("No year value recognized. Continuing search with title only.");
            	}
            }
        }

        // set year if needed
        String url = keyWord.constructSearch();

        System.out.println("url"+ url);
        MovieApiConnection c = new MovieApiConnection(url); 
        String cs = c.requestGET();

        DataParser dpc = new DataParser(cs);

        fileName = searcKeyword.replaceAll(" ", "")+".csv";
        
        if(dpc.processClob().size() <= 1){
        	fileName = "";
        	return true;
        } else { //(dpc.processClob().size() > 1)
            // if we got something back, then print to csv
            FileHandler.MovieSearchedWriter(fileName, dpc.processClob());
        }
        //in.close();

        
        return true;
	}
	
	public void parseSearchResult() {
		
		File f = new File(fileName);
		
		try {
			Scanner in = new Scanner(f);
			while (in.hasNextLine()) {
				String[] line = in.nextLine().split(",");
				searchResult.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public void addToCart() {
		int addToCartNumber;

		Scanner in = new Scanner(System.in);

		boolean loop = true;

		while(loop) {
			System.out.println("Enter number of the movie you want to add to cart [q to go back to search]");

			String input = in.nextLine();

			if (input.toLowerCase().equals("q")) {
				return;
			}
			else {
				try {
					addToCartNumber = Integer.parseInt(input);
					
					if (addToCartNumber < 1 || addToCartNumber > searchResult.size()) {
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
							System.out.println("Movie number " + addToCartNumber + ", " + title + " added to cart,");
						}
					}
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
		}
		
	}
	
	public void run() {
		
		Display display = new Display();
		
		display.storeFront(); 
		
		boolean loop = true;
		
		while (loop) {
			
			loop = userSearchOld();
			
			if (!fileName.isEmpty()) {
				parseSearchResult();
				display.searchResult(searchResult);
				addToCart();
			}
		}
		userCart.displayCart();
		display.exit();
	}
	
	public static void main(String[] args) {
		InitialBrowsing test = new InitialBrowsing();
		test.run();

	}
}
