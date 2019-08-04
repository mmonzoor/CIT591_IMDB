import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class CartTest {
	
	Cart testCart = new Cart();
    
	String[] arr1 = { "one", "two", "three", "four", "five" };
	String[] arr2 = { "hana", "dool", "saet", "net", "dasut" };
	
	@Test
	void testGetCartSize() {
		testCart.addToCart(arr1);
		testCart.addToCart(arr2);
		assertEquals(2, testCart.getCartSize());
	}
	
	@Test
	void testIsEmpty() {
		testCart.addToCart(arr1);
		assertFalse(testCart.isEmpty());
	}
	
	@Test
	void testIsAlreadyInCart() {
		testCart.addToCart(arr1);
		String[] arrToCompare = { "one", "two", "three", "four", "five" };
		assertTrue(testCart.isAlreadyInCart(arrToCompare));
	}
	
	@Test
	void testGet() {
		testCart.addToCart(arr1);
		String[] arr1 = { "one", "two", "three", "four", "five" };
		assertEquals(arr1[1], testCart.get(0)[1]);
	}

	@Test
	void testClearCart() {
		testCart.clearCart();
		assertTrue(testCart.isEmpty());
	}

}
