import org.junit.jupiter.api.Test;

class SearchMoviesTest {

	@Test
	void testConstructSearch() {
		SearchMovies testMovie = new SearchMovies("the avengers");
		String testURL = testMovie.constructSearch();
		assert(testURL.contains("&s=the+avengers"));
	}

}
