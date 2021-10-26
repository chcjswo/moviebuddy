package com.mocadev.moviebuddy.data;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-10-20
 **/
public class CsvMovieReaderTest {

	@Test
	void validMetadata() throws Exception {
		final CsvMovieReader movieReader = new CsvMovieReader();
		movieReader.setMetaData("movie_metadata.csv");
		movieReader.setResourceLoader(new DefaultResourceLoader());
		movieReader.afterPropertiesSet();
	}

	@Test
	void invalidMetadata() {
		final CsvMovieReader movieReader = new CsvMovieReader();
		movieReader.setResourceLoader(new DefaultResourceLoader());
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			movieReader.setMetaData("invalid");
			movieReader.afterPropertiesSet();
		});
	}

}
