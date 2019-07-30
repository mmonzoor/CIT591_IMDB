import java.util.ArrayList;

/**
 * Shopping cart that holds movies 
 * @author Nami Kim
 */
public class Cart {
	
	private ArrayList<String[]> cart = new ArrayList<>();
	
	public int getCartSize() {
		return cart.size();
	}
	
	public void addToCart(String[] userChoiceMovie) {
		cart.add(userChoiceMovie);
	}
	
	public void removeFromCart(int removeFromCartNumber) {
		cart.remove(removeFromCartNumber - 1);
	}
	
	public void displayCart() {
		
		Display display = new Display();
		display.cart(cart);
	}
	
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
