import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

class InitialBrowsingTest {

	InitialBrowsing testBrowsing = new InitialBrowsing();

	@Test
	void testAddToCart() {
		// replace user input with "r"
		ByteArrayInputStream in = new ByteArrayInputStream("r".getBytes());
		System.setIn(in);
		
		testBrowsing.addToCart();
		assertEquals(0, testBrowsing.getUserCart().getCartSize());
	}

	@Test
	void testInputHandling1() {
		// replace user input with "S"
		ByteArrayInputStream in = new ByteArrayInputStream("S".getBytes());
		System.setIn(in);
	
		assertEquals('s', testBrowsing.inputHandling());
	}
	
	@Test
	void testInputHandling2() {
		// replace user input with "S"
		ByteArrayInputStream in = new ByteArrayInputStream("C".getBytes());
		System.setIn(in);
	
		assertEquals('c', testBrowsing.inputHandling());
	}

	@Test
	void testInputHandling3() {
		// replace user input with "S"
		ByteArrayInputStream in = new ByteArrayInputStream("Q".getBytes());
		System.setIn(in);
	
		assertEquals('q', testBrowsing.inputHandling());
	}

}
