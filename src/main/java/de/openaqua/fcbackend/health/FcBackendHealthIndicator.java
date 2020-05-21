package de.openaqua.fcbackend.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 
 * @author abehrens
 * 
 *         Concept from here:
 *         - https://www.callicoder.com/spring-boot-actuator/
 *         -
 *         https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html
 *
 */
@Component
public class FcBackendHealthIndicator extends AbstractHealthIndicator {
  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {
    // Use the builder to build the health status details that should be reported.
    // If you throw an exception, the status will be DOWN with the exception message.

    builder.up()
        //
        .withDetail("app", "Alive and Kicking")
        //
        .withDetail("error", "Nothing! I'm good.");
  }
}
