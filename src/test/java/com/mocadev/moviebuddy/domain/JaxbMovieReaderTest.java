package com.mocadev.moviebuddy.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-29
 **/
class JaxbMovieReaderTest {

	@Test
	void notEmpty_LoadMovies() {
		JaxbMovieReader jaxbMovieReader = new JaxbMovieReader();
		final List<Movie> movies = jaxbMovieReader.loadMovies();
		for (Movie movie : movies) {
			System.out.println("movie = " + movie);
		}
		Assertions.assertEquals(1375, movies.size());
	}

}
