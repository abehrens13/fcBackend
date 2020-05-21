/**
 *
 */
package de.openaqua.fcbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableJpaRepositories
@SpringBootApplication(scanBasePackages = { "de.openaqua.fcbackend", "io.swagger.api",
    "io.swagger.model" }, exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class })
public class FcBackendApplication {
  private static final Logger LOG = LoggerFactory.getLogger(FcBackendApplication.class);

  public static void main(String[] args) {
    LOG.debug("FcBackendApplication::main()");
    SpringApplication.run(FcBackendApplication.class);

  }
}
