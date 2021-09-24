package com.mocadev.moviebuddy.util;

import com.mocadev.moviebuddy.ApplicationException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-24
 **/
public class FileSystemUtils {

	public static URI checkFileSystem(URI uri) {
		if("jar".equalsIgnoreCase(uri.getScheme())){
			for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
				if (provider.getScheme().equalsIgnoreCase("jar")) {
					try {
						provider.getFileSystem(uri);
					} catch (FileSystemNotFoundException ignore) {
						// in this case we need to initialize it first
						try {
							provider.newFileSystem(uri, Collections.emptyMap());
						} catch (IOException error) {
							throw new ApplicationException("failed to initialize file system.", error);
						}
					}
				}
			}
		}
		return uri;
	}

}
