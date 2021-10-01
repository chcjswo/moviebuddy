package com.mocadev.moviebuddy;

import com.mocadev.moviebuddy.domain.MovieFinder;
import com.mocadev.moviebuddy.domain.MovieReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-30
 **/
@Configuration
@Import({MovieBuddyFactory.DataSourceModuleConfig.class,
MovieBuddyFactory.DomainModuleConfig.class})
public class MovieBuddyFactory {

	@Configuration
	@ComponentScan
	static class DomainModuleConfig {
		@Bean
		public MovieFinder movieFinder(MovieReader movieReader) {
			return new MovieFinder(movieReader);
		}
	}

	@Configuration
	 static class DataSourceModuleConfig {
	}

}
