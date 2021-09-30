package com.mocadev.moviebuddy.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mocadev.moviebuddy.MovieBuddyFactory;
import java.util.List;
import org.junit.jupiter.api.Test;

class MovieFinderTest {

	@Test
	void mainTest() {
		MovieBuddyFactory movieBuddyFactory = new MovieBuddyFactory();
		MovieFinder movieFinder = movieBuddyFactory.movieFinder();

		List<Movie> result = movieFinder.directedBy("Michael Bay");
		assertEquals(3, result.size());

		result = movieFinder.releasedYearBy(2015);
		assertEquals(225, result.size());
	}

}
