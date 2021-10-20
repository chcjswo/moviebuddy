package com.mocadev.moviebuddy.data;

import com.mocadev.moviebuddy.ApplicationException;
import com.mocadev.moviebuddy.MovieBuddyProfile;
import com.mocadev.moviebuddy.domain.Movie;
import com.mocadev.moviebuddy.domain.MovieReader;
import com.mocadev.moviebuddy.util.FileSystemUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-28
 **/
@Repository
@Profile(MovieBuddyProfile.CSV_MODE)
public class CsvMovieReader extends AbstractFileSystemMovieReader implements MovieReader {

	@Override
	public List<Movie> loadMovies() {
		try {
			final URI resourceUri = ClassLoader.getSystemResource(getMetaData()).toURI();
			final Path data = Path.of(FileSystemUtils.checkFileSystem(resourceUri));
			final Function<String, Movie> mapCsv = csv -> {
				try {
					// split with comma
					String[] values = csv.split(",");

					String title = values[0];
					List<String> genres = Arrays.asList(values[1].split("\\|"));
					String language = values[2].trim();
					String country = values[3].trim();
					int releaseYear = Integer.parseInt(values[4].trim());
					String director = values[5].trim();
					List<String> actors = Arrays.asList(values[6].split("\\|"));
					URL imdbLink = new URL(values[7].trim());
					String watchedDate = values[8];

					return Movie.of(title, genres, language, country, releaseYear, director, actors,
						imdbLink, watchedDate);
				} catch (IOException error) {
					throw new ApplicationException("mapping csv to object failed.", error);
				}
			};

			return Files.readAllLines(data, StandardCharsets.UTF_8)
				.stream()
				.skip(1)
				.map(mapCsv)
				.collect(Collectors.toList());
		} catch (IOException | URISyntaxException error) {
			throw new ApplicationException("failed to load movies data.", error);
		}
	}

}
