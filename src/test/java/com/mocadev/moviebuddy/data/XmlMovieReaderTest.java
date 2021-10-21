package com.mocadev.moviebuddy.data;

import com.mocadev.moviebuddy.MovieBuddyFactory;
import com.mocadev.moviebuddy.MovieBuddyProfile;
import com.mocadev.moviebuddy.domain.Movie;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-29
 **/
@ActiveProfiles(MovieBuddyProfile.XML_MODE)
@SpringJUnitConfig(MovieBuddyFactory.class)
@TestPropertySource(properties = "movie.metadata=movie_metadata.xml")
class XmlMovieReaderTest {

	@Autowired
	XmlMovieReader xmlMovieReader;

	@Test
	void notEmpty_LoadMovies() {
		final List<Movie> movies = xmlMovieReader.loadMovies();
		for (Movie movie : movies) {
			System.out.println("movie = " + movie);
		}
		Assertions.assertEquals(1375, movies.size());
	}

}
