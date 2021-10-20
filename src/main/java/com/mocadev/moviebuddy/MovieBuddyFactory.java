package com.mocadev.moviebuddy;

import com.mocadev.moviebuddy.data.CsvMovieReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

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
@ComponentScan
public class MovieBuddyFactory {

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.mocadev.moviebuddy");

		return marshaller;
	}

	@Configuration
	static class DomainModuleConfig {
	}

	@Configuration
	 static class DataSourceModuleConfig {

		@Profile(MovieBuddyProfile.CSV_MODE)
		@Bean
		public CsvMovieReader csvMovieReader() {
			CsvMovieReader movieReader = new CsvMovieReader();
			movieReader.setMetaData("movie_metadata.csv");
			return movieReader;
		}

	}

}
