package hu.bme.mit.trainbenchmark.benchmark.epsilon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class EpsilonApiUtil {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonApiUtil.class);


	/**
	 * Create a temp file from a resource's input stream. The stream can be created from a resource
	 * in a class or with the system class loader:
	 * <code>
	 * 		InputStream resourceInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
	 * 		InputStream resourceInputStream = SomeClass.class.getResourceAsStream(resourcePath);
	 * </code>
	 * @param resourceInputStream
	 * @return
	 */
	public static File getResourceAsFile(InputStream resourceInputStream) {

		if (resourceInputStream == null) {
			return null;
		}

		File tempFile = null;
		try {
			tempFile = File.createTempFile(String.valueOf(resourceInputStream.hashCode()), ".tmp");
		} catch (IOException e) {
			logger.error("Error creating temp file for resource.", e);
		}
		finally {
			if (tempFile != null) {
				tempFile.deleteOnExit();
				try (FileOutputStream out = new FileOutputStream(tempFile)) {
					//copy stream
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = resourceInputStream.read(buffer)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
				} catch (IOException e) {
					logger.error("Error copying resource to temp file.", e);
				}
			}
		}
		return tempFile;
	}
}
