package de.openaqua.fcbackend.redis;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import de.openaqua.fcbackend.entities.QuizzSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuizzSessionRepositoryTest {
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
    log.info("testSave");
    // prepare new object
    QuizzSession a = new QuizzSession();
    QuizzSession b = repository.save(a);
    QuizzSession c = repository.findById(a.getId()).get();
    log.error("=========================redis forgets the time");
    log.error("5: " + c.toString());
    log.error("=========================redis forgets the time");
    assertNotNull(c.getCreationTime());
    assertEquals(a, b);
    assertEquals(b, c);
    repository.deleteById(a.getId());
  }

  @Test
  void testFindById() {
    QuizzSession a = repository.save(new QuizzSession());
    Optional<QuizzSession> b = repository.findById(a.getId());
    assertTrue(b.isPresent());
    if (b.isPresent()) {
      assertEquals(a, b.get());
    }

  }

  @Test
  void testCount() {
    long a = repository.count();
    repository.save(new QuizzSession());
    long b = repository.count();
    assertTrue(b - a == 1);
  }

  @Test
  void testDeleteAll() {
    repository.save(new QuizzSession());
    long a = repository.count();
    repository.deleteAll();
    long b = repository.count();
    assertTrue(b < a);
    assertTrue(b == 0);
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
