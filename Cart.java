import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Shopping cart that holds movies user chooses from search results. 
 * @author Nami Kim
 */
public class Cart {
	
	private ArrayList<String[]> cart = new ArrayList<>();
	
	/**
	 * Getter 
	 * @return cart ArrayList<String[]> 
	 */
	public ArrayList<String[]> getCart() {
		return cart;
	}
	
	/**
	 * Get specific index at cart, return String[] at the index
	 * @param i integer index 
	 * @return String[]
	 */
	public String[] get(int i) {
		return cart.get(i);
	}

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
	 * Check if selected item is already in cart and return boolean. 
	 * @param userChoiceMovie String array of movie info
	 * @return boolean whether the item is already in cart or not 
	 */
	public boolean isAlreadyInCart(String[] userChoiceMovie) {
		
		if (cart.isEmpty()){
			return false;
		}
		// compare IMDB id (index 3) to see if movie already in cart 
		for (String[] movieInCart : cart) {
			if (movieInCart[3].equals(userChoiceMovie[3])) {
				return true; 
			}
					
		}	
		return false; 
	}
	
	/**
	 * Remove all element in cart 
	 */
	public void clearCart() {
		cart.removeAll(cart);
	}
	
	/**
	 * Check if the cart is empty 
	 */
	public boolean isEmpty() {
		return cart.isEmpty();
	}
	
}
