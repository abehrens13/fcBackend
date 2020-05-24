package de.openaqua.fcbackend.redis;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import de.openaqua.fcbackend.entities.QuizzSession;

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
    assertTrue(a.equals(b));
  }

  @Test
  void testFindById() {
    QuizzSession a = new QuizzSession();
    repository.save(a);
    Optional<QuizzSession> b = repository.findById(a.getId());
    assertTrue(b.isPresent());
    assertTrue(a.equals(b.get()));
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
