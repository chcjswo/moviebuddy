package com.mocadev.moviebuddy.data;

import com.mocadev.moviebuddy.ApplicationException;
import com.mocadev.moviebuddy.MovieBuddyProfile;
import com.mocadev.moviebuddy.domain.Movie;
import com.mocadev.moviebuddy.domain.MovieData;
import com.mocadev.moviebuddy.domain.MovieReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.springframework.context.annotation.Profile;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Repository;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-29
 **/
@Repository
@Profile(MovieBuddyProfile.XML_MODE)
public class XmlMovieReader extends AbstractMetadataResourceMovieReader implements MovieReader {

	private final Unmarshaller unmarshaller;

	public XmlMovieReader(Unmarshaller unmarshaller) {
		this.unmarshaller = Objects.requireNonNull(unmarshaller);
	}

	@Override
	public List<Movie> loadMovies() {
		try {
			final InputStream content = getMetadataResource().getInputStream();
			final Source source = new StreamSource(content);
			final MovieMetadata movieMetadata = (MovieMetadata) unmarshaller.unmarshal(source);

			return movieMetadata.toMovies();
		} catch (IOException e) {
			throw new ApplicationException("failed to load movies data: ", e);
		}
	}

	@XmlRootElement(name = "moviemetadata")
	public static class MovieMetadata {

		private List<MovieData> movies;

		public List<MovieData> getMovies() {
			return movies;
		}

		public void setMovies(List<MovieData> movies) {
			this.movies = movies;
		}

		public List<Movie> toMovies() {
			return movies.stream()
				.map(MovieData::toMovie)
				.collect(Collectors.toList());
		}

	}

}
