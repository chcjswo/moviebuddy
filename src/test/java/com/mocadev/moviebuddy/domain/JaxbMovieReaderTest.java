package com.mocadev.moviebuddy.domain;

import java.util.List;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-29
 **/
public class JaxbMovieReaderTest {

	public static void main(String[] args) {
		JaxbMovieReader jaxbMovieReader = new JaxbMovieReader();
		final List<Movie> movies = jaxbMovieReader.loadMovies();
		for (Movie movie : movies) {
			System.out.println("movie = " + movie);
		}
		System.out.println("movies.size() = " + movies.size());
	}

}
