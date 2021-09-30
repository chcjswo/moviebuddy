package com.mocadev.moviebuddy;

import com.mocadev.moviebuddy.domain.CsvMovieReader;
import com.mocadev.moviebuddy.domain.MovieFinder;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-30
 **/
public class MovieBuddyFactory {

	public MovieFinder movieFinder() {
		return new MovieFinder(new CsvMovieReader());
	}

}
