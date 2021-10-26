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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-10-20
 **/
public abstract class AbstractMetadataResourceMovieReader implements ResourceLoaderAware {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private String metaData;
	private ResourceLoader resourceLoader;

	public String getMetaData() {
		return metaData;
	}

	@Value("${movie.metadata}")
	public void setMetaData(String metaData) {
		this.metaData = Objects.requireNonNull(metaData, "metadata is required value");
	}

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		Resource resource = getMetadataResource();
		if (!resource.exists()) {
			throw new FileNotFoundException(metaData);
		}
		if (!resource.isReadable()) {
			throw new ApplicationException(
				String.format("cannot read to metadata. [%s]", metaData));
		}
		log.info(resource + " is ready");
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Resource getMetadataResource() {
		return resourceLoader.getResource(getMetaData());
	}

	public URL getMetadataUrl() {
		String location = getMetaData();
		if (location.startsWith("file:")) {

		} else if (location.startsWith("http:")) {

		}
		return ClassLoader.getSystemResource(location);
	}

	@PreDestroy
	public void destroy() throws Exception {
		log.info("destroy bean");
	}

}
