/**
 *
 */
package de.openaqua.fcbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "de.openaqua.fcbackend", "de.openaqua.fcbackend.controller" })
public class FcBackendApplication {
  private static final Logger LOG = LoggerFactory.getLogger(FcBackendApplication.class);

  public static void main(String[] args) {
    LOG.debug("FcBackendApplication::main()");
    SpringApplication.run(FcBackendApplication.class, args);

  }
}
