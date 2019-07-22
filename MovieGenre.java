import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parse movie genre file, keep String array of available movie genres
 * @author Nami Kim
 *
 */
public class MovieGenre {
	private String[] genre;
	
	/** 
	 * Getter
	 * @return
	 */
	public String[] getGenre() {
		return genre;
	}

	/**
	 * Parse genre.txt file, store in instance variable of String array. 
	 */
	public MovieGenre() {
		File f = new File("src/genre.txt");
		try {
			Scanner in = new Scanner(f);
			
			genre = in.nextLine().split(",");
			
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
