import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class MovieGenreTest {

	@Test
	void testGetGenre() {
		MovieGenre test = new MovieGenre();
		assertEquals( 7, test.getGenre().length ,  "Check you have all genres parsed" );
	}

}
