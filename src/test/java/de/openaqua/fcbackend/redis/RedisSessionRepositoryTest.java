package de.openaqua.fcbackend.redis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class RedisSessionRepositoryTest {
  @Autowired
  private RedisSessionRepository repository;

  @Test
  void testSave() {
    RedisSession a = new RedisSession();
    RedisSession b = repository.save(a);
    assertTrue(a.equals(b));
    repository.delete(b);
    Optional<RedisSession> c = repository.findById(a.getId());
    assertFalse(c.isPresent());
  }
}
