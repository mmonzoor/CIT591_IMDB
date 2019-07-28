import java.util.Scanner;

/**
 * This is the front-end where the user  makes searches. 
 * @author Nami Kim
 */
public class InitialBrowsing {
	
	
	/** 
	 * Ask user for search keyword, year, pass on to API, retrieve results in a CSV file. 
	 */
	public void userSearch() {
		
		System.out.println("please enter the title of the movie to be searched.");
        Scanner in = new Scanner(System.in);
        
        String searcKeyword = in.nextLine();
        // Search URL construction

        SearchMovies keyWord = new SearchMovies(searcKeyword);
		
        // prompt for year 
        Scanner yin = new Scanner(System.in);
        System.out.println("would you like to input a year to search with aswell?(y/n)");
        
        String choice = yin.nextLine().toLowerCase();
        if(choice.equals("y") || choice.equals("yes")){
            Scanner yearInput = new Scanner(System.in);
            System.out.println("What year would you like to search by? ");
            String year = yearInput.nextLine();
            keyWord.setYear(Integer.parseInt(year));
            yearInput.close();
        }
        else{
            System.out.println("No year value recognized. Continuing search with title only.");
        }

        // set year if needed
        String url = keyWord.constructSearch();

        System.out.println("url"+ url);
        MovieApiConnection c = new MovieApiConnection(url);
        String cs = c.requestGET();

        DataParser dpc = new DataParser(cs);

        String fileName = searcKeyword.replaceAll(" ", "")+".csv";
        
        System.out.println(dpc.processClob().size());
        
        if(dpc.processClob().size() > 1){
            // if we got something back, then print to csv
            FileHandler.MovieSearchedWriter(fileName, dpc.processClob());
        }
        in.close();
        yin.close();
        
	}
	
	public static void main(String[] args) {
		
		Display testDisplay = new Display();
		testDisplay.StandardFrontScreen();
		
		InitialBrowsing test = new InitialBrowsing();
		test.userSearch();

	}
}
