package com.mocadev.moviebuddy.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class MovieFinderTest {

	@Test
	void mainTest() {
		MovieFinder movieFinder = new MovieFinder();

		List<Movie> result = movieFinder.directedBy("Michael Bay");
		assertEquals(3, result.size());

		result = movieFinder.releasedYearBy(2015);
		assertEquals(225, result.size());
	}

}
