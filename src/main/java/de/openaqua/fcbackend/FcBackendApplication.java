package de.openaqua.fcbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import({ WebConfig.class })
public class FcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcBackendApplication.class, args);
	}

}
