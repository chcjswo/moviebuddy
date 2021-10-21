package com.mocadev.moviebuddy;

import com.mocadev.moviebuddy.data.AbstractFileSystemMovieReader;
import com.mocadev.moviebuddy.data.CsvMovieReader;
import com.mocadev.moviebuddy.data.XmlMovieReader;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.oxm.Unmarshaller;
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

		private final Environment env;

		public DataSourceModuleConfig(Environment env) {
			this.env = env;
		}

		@Profile(MovieBuddyProfile.CSV_MODE)
		@Bean
		public CsvMovieReader csvMovieReader() {
			CsvMovieReader movieReader = new CsvMovieReader();

			movieReader.setMetaData(env.getProperty("movie_metadata"));
			return movieReader;
		}

		@Profile(MovieBuddyProfile.XML_MODE)
		@Bean
		public XmlMovieReader xmlMovieReader(Unmarshaller unmarshaller) {
			XmlMovieReader movieReader = new XmlMovieReader(unmarshaller);
			movieReader.setMetaData(env.getProperty("movie_metadata"));
			return movieReader;
		}

	}

}
