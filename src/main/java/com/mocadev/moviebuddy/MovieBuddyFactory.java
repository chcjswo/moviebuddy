package com.mocadev.moviebuddy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

	@Configuration
	static class DomainModuleConfig {
	}

	@Configuration
	 static class DataSourceModuleConfig {
	}

}
