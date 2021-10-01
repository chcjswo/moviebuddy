package com.mocadev.moviebuddy;

import com.mocadev.moviebuddy.domain.CsvMovieReader;
import com.mocadev.moviebuddy.domain.MovieFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-30
 **/
@Configuration
public class MovieBuddyFactory {

	@Bean
	public MovieFinder movieFinder() {
		return new MovieFinder(new CsvMovieReader());
	}

}
