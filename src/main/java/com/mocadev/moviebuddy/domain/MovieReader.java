package com.mocadev.moviebuddy.domain;

import java.util.List;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-28
 **/
public interface MovieReader {

	List<Movie> loadMovies();

}
