package de.openaqua.fcbackend;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;


@Component
public class JavaConfig {
	@Bean
	public SerialGenerator serialGenerator() {
		return new SerialGenerator("TEST", 40000);
	}

}
