package com.mocadev.moviebuddy.domain;

import com.mocadev.moviebuddy.MovieBuddyFactory;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-29
 **/
@SpringJUnitConfig(MovieBuddyFactory.class)
class JaxbMovieReaderTest {

	@Autowired
	JaxbMovieReader jaxbMovieReader;

	@Test
	void notEmpty_LoadMovies() {
		final List<Movie> movies = jaxbMovieReader.loadMovies();
		for (Movie movie : movies) {
			System.out.println("movie = " + movie);
		}
		Assertions.assertEquals(1375, movies.size());
	}

}
