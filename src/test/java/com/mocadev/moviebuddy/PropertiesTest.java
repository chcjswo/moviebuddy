package com.mocadev.moviebuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.junit.jupiter.api.Test;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-10-21
 **/
public class PropertiesTest {

	@Test
	void load_PropertiesFile() throws IOException {
		Properties config = new Properties();
		config.load(Files.newInputStream(Paths.get("./src/test/resources/config.properties")));
		assertEquals(1, config.size());
		assertEquals("mocadev", config.get("name"));
	}

}
