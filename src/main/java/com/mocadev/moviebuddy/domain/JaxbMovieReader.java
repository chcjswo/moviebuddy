package com.mocadev.moviebuddy.domain;

import com.mocadev.moviebuddy.ApplicationException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.springframework.stereotype.Repository;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-29
 **/
@Repository
public class JaxbMovieReader implements MovieReader {

	@Override
	public List<Movie> loadMovies() {
		try {
			final JAXBContext jaxb = JAXBContext.newInstance(MovieMetadata.class);
			final Unmarshaller unmarshaller = jaxb.createUnmarshaller();

			final InputStream content = ClassLoader.getSystemResourceAsStream("movie_metadata.xml");
			final Source source = new StreamSource(content);
			final MovieMetadata movieMetadata = (MovieMetadata) unmarshaller.unmarshal(source);

			return movieMetadata.toMovies();
		} catch (JAXBException e) {
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
