import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

class InitialBrowsingTest {

	InitialBrowsing testBrowsing = new InitialBrowsing();

	@Test
	void testParseSearchResult() {
		Path p = Path.get("/test.csv").normalize().toString();
		testBrowsing.setFileName(p);
		testBrowsing.parseSearchResult();
		assertEquals("batman v superman: dawn of justice", testBrowsing.getSearchResult().get(1)[1]);
		assertEquals("tt2975590", testBrowsing.getSearchResult().get(1)[3]);
		assertEquals("14.74", testBrowsing.getSearchResult().get(2)[4]);
	}

	@Test
	void testParseLongTitle() {
		testBrowsing.setFileName("test.csv");
		testBrowsing.parseSearchResult();
		assertEquals(45, testBrowsing.getSearchResult().get(2)[1].length());
	}
	
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
