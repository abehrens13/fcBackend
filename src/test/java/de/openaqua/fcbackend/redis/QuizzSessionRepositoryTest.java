package de.openaqua.fcbackend.redis;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import de.openaqua.fcbackend.controller.QuestionController;
import de.openaqua.fcbackend.entities.QuizzSession;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuizzSessionRepositoryTest {
  private static final Logger LOG = LoggerFactory.getLogger(QuestionController.class);
  @LocalServerPort
  int randomServerPort;

  @Autowired
  QuizzSessionRepository repository;

  @Test
  @Order(1)
  void testEnvironment() {
    assertNotNull(repository);
  }

  @Test
  void testSave() {
    QuizzSession a = new QuizzSession();
    QuizzSession b = repository.save(a);
    assertTrue(a.getId().equals(b.getId()));
  }

  @Test
  void testSave2() throws InterruptedException {
    QuizzSession a = new QuizzSession();
    a.setCreationTime(OffsetDateTime.now(ZoneOffset.UTC));
    LOG.debug("1: " + a.toString());
    TimeUnit.SECONDS.sleep(2);
    LOG.debug("2: " + a.toString());

    QuizzSession b = repository.save(a);
    LOG.debug("3: " + a.toString());
    LOG.debug("4: " + b.toString());
    TimeUnit.SECONDS.sleep(2);

    QuizzSession c = repository.findById(a.getId()).get();
    LOG.debug("5: " + c.toString());
    assertTrue(a.getId().equals(b.getId()));
  }

  @Test
  void testFindById() {
    QuizzSession a = repository.save(new QuizzSession());
    Optional<QuizzSession> b = repository.findById(a.getId());
    assertTrue(b.isPresent());
    assertTrue(a.getId().equals(b.get().getId()));
  }

  @Test
  void testCount() {
    long a = repository.count();
    repository.save(new QuizzSession());
    long b = repository.count();
    assertTrue(b - a == 1);
  }

  @Test
  void testDeleteById() {
    QuizzSession a = new QuizzSession();
    repository.save(a);
    repository.deleteById(a.getId());
    Optional<QuizzSession> b = repository.findById(a.getId());
    assertFalse(b.isPresent());
  }

  @Test
  void testDelete() {
    QuizzSession a = new QuizzSession();
    repository.save(a);
    repository.delete(a);
    Optional<QuizzSession> b = repository.findById(a.getId());
    assertFalse(b.isPresent());
  }

}
