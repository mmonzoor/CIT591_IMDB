import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheckoutTest {

	@Test
	public void testBillCart1() {
		Cart cart = new Cart();
		Checkout checkout = new Checkout();
		String[] movie1 = { "1", "Batman", "1989", "tt0096895", "3.45" };
		cart.addToCart(movie1);
		double bill = checkout.calculateBill(cart);
		assertEquals(3.45, bill, 0.01);
	}

	@Test
	public void testBillCart2() {
		Cart cart = new Cart();
		Checkout checkout = new Checkout();
		String[] movie1 = { "1", "Batman", "1989", "tt0096895", "15.333" };
		String[] movie2 = { "2", "Superman", "1978", "tt0078346", "8.35555556" };
		cart.addToCart(movie1);
		cart.addToCart(movie2);
		double bill = checkout.calculateBill(cart);
		assertEquals(23.69, bill, 0.01);
	}

	@Test
	public void testBillCart3() {
		Cart cart = new Cart();
		Checkout checkout = new Checkout();
		String[] movie1 = { "1", "Batman", "1989", "tt0096895", "14" };
		String[] movie2 = { "2", "Superman", "1978", "tt0078346", "0" };
		String[] movie3 = { "3", "Gone with the Wind", "1939", "tt0031381", "9.22" };
		cart.addToCart(movie1);
		cart.addToCart(movie2);
		cart.addToCart(movie3);
		double bill = checkout.calculateBill(cart);
		assertEquals(23.22, bill, 0.01);
	}

	@Test
	public void testBillEmptyCart() {
		Cart cart = new Cart();
		Checkout checkout = new Checkout();
		double bill = checkout.calculateBill(cart);
		assertEquals(0.00, bill, 0.01);
	}

	@Test
	public void testBillNumFormatCatch() {
		Cart cart = new Cart();
		Checkout checkout = new Checkout();
		String[] movie1 = { "1", "Batman", "1989", "tt0096895", "p" };
		cart.addToCart(movie1);
		double bill = checkout.calculateBill(cart);
		assertEquals(0.00, bill, 0.01);
	}

}
