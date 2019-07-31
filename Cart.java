import java.util.ArrayList;

/**
 * Shopping cart that holds movies user chooses from search results. 
 * @author Nami Kim
 */
public class Cart {
	
	private ArrayList<String[]> cart = new ArrayList<>();
	
	/**
	 * Return number of movies in the cart.
	 * @return integer number of movies in the cart
	 */
	public int getCartSize() {
		return cart.size();
	}
	
	/**
	 * Add a movie to the cart.
	 * @param userChoiceMovie String array of movie information
	 */
	public void addToCart(String[] userChoiceMovie) {
		cart.add(userChoiceMovie);
	}
	
	/** 
	 * Remove a chosen movie from the cart 
	 * @param removeFromCartNumber index number in ArrayList where movie is located
	 */
	public void removeFromCart(int removeFromCartNumber) {
		cart.remove(removeFromCartNumber - 1);
	}
	
	/**
	 * Display content in the cart. 
	 */
	public void displayCart() {
		Display display = new Display();
		display.cart(cart);
	}
	
	/**
	 * Check if selected item is already in cart and return boolean. 
	 * @param userChoiceMovie String array of movie info
	 * @return boolean whether the item is already in cart or not 
	 */
	public boolean isAlreadyInCart(String[] userChoiceMovie) {
		
		if (cart.isEmpty()){
			return false;
		}
		 
		for (String[] movieInCart : cart) {
			if (movieInCart[3].equals(userChoiceMovie[3])) { // compare IMDB id 
				return true; 
			}
					
		}	
		return false; 
	}
	
}
