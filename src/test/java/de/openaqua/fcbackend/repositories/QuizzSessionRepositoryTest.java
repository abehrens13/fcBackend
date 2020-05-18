package de.openaqua.fcbackend.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.openaqua.fcbackend.entities.QuizzSession;

@SpringBootTest()
class QuizzSessionRepositoryTest {
  @Autowired
  private QuizzSessionRepository repository;

  @Test
  void testSave() {
    QuizzSession a = new QuizzSession();
    QuizzSession b = repository.save(a);
    assertTrue(a.equals(b));
    repository.delete(b);
    Optional<QuizzSession> c = repository.findById(a.getId());
    assertFalse(c.isPresent());
  }
}
