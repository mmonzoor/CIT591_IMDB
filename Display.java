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
		System.out.printf("%45s", "Welcome to " + storeTitle + "\n");
	}
		
	/**
	 * Display a horizontal bar on screen.
	 */
	public void horizontalBar() {
		
		int horizontalSpaceSize = 60;
		
		for (int i = 0; i < horizontalSpaceSize ; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	/**
	 * Display exit message 
	 */
	public void exit() {
		System.out.println("Thank you. Have a nice day");
	}
	
	public void StandardFrontScreen() {
		welcomeUser();
		horizontalBar();
	}
}
