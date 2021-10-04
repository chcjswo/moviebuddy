package com.mocadev.moviebuddy.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mocadev.moviebuddy.MovieBuddyFactory;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(MovieBuddyFactory.class)
class MovieFinderTest {

	@Autowired
	MovieFinder movieFinder;

	@Test
	void notEmpty_directedBy() {
		final List<Movie> movies = movieFinder.directedBy("Michael Bay");
		Assertions.assertEquals(3, movies.size());
	}

	@Test
	void notEmpty_releasedBy() {
		List<Movie> result = movieFinder.releasedYearBy(2015);
		assertEquals(225, result.size());
	}

}
