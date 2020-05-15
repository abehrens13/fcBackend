/**
 * 
 */
package de.openaqua.fcbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FcBackendApplication {
	private static final Logger LOG = LoggerFactory.getLogger(FcBackendApplication.class);

	public static void main(String... args) {
		LOG.debug("FcBackendApplication::main()");
		SpringApplication application = new SpringApplication(FcBackendApplication.class);
		application.run(args);

	}
}
