package com.mocadev.moviebuddy.data;

import com.mocadev.moviebuddy.ApplicationException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-10-20
 **/
public abstract class AbstractFileSystemMovieReader {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private String metaData;

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = Objects.requireNonNull(metaData, "metadata is required value");
	}

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		final URL url = ClassLoader.getSystemResource(metaData);
		if (Objects.isNull(url)) {
			throw new FileNotFoundException(metaData);
		}
		if (!Files.isReadable(Path.of(url.toURI()))) {
			throw new ApplicationException(
				String.format("cannot read to metadata. [%s]", metaData));
		}
	}

	@PreDestroy
	public void destroy() throws Exception {
		log.info("destroy bean");
	}

}
