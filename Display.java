import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains screen displays  
 * @author Nami Kim
 */
public class Display {
	private String storeTitle = "JD-NK-MM Movie Rental";
	
	/**
	 * Display welcome message.
	 */
	public void welcomeUser() {
		System.out.printf("%50s", "Welcome to " + storeTitle + "\n");
		 horizontalBar();
	}
		
	/**
	 * Display a horizontal bar on screen.
	 */
	public void horizontalBar() {
		
		int horizontalSpaceSize = 70;
		
		for (int i = 0; i < horizontalSpaceSize ; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	/**
	 * Display exit message 
	 */
	public void exit() {
		System.out.println("Thank you. Have a nice day.");
	}
	
	public void storeFront() {
		welcomeUser();
		horizontalBar();
	}
	
	/**
	 * Display search results in the CSV file. 
	 * @param filename
	 */
	public void searchResult(ArrayList<String[]> searchResult) {
		horizontalBar(); 
		System.out.printf("%3s %-45s %-10s %-10s\n", "No.", "Title", "Year", "Price");
		
		for (int i = 1; i < searchResult.size(); i++) {
			System.out.printf("%-3s %-45s %-10s %-10s\n", i, searchResult.get(i)[1], searchResult.get(i)[2], searchResult.get(i)[4]);			
		}
		horizontalBar();
	}
	
	public void cart(ArrayList<String[]> cart) {
		 
		horizontalBar();
		if (cart.isEmpty()) {
			System.out.printf("%45s", "Your cart is empty\n");
		} else {
			System.out.printf("%3s %-45s %-10s %-10s\n", "No.", "Title", "Year", "Price");
			
			for (int i = 0; i < cart.size(); i++) {
				System.out.printf("%-3s %-45s %-10s %-10s\n", i + 1, cart.get(i)[1], cart.get(i)[2], cart.get(i)[4]);			
			}
		}
		horizontalBar();
	}
	
	public void noSearchResult() {
		horizontalBar();
		System.out.printf("%45s", "No search result found\n");
		horizontalBar();
	}
	
	public static void main(String[] args) {
		
		String[] test = {"0","123456789012345678901234567890123456789012345","1234567890","-","-"};
		
		ArrayList<String[]> arr = new ArrayList<>();
		arr.add(test);
		
		Display dp = new Display();
		dp.cart(arr);
	}
}
