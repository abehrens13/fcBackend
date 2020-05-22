package de.openaqua.fcbackend.redis;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class RedisRepositoryTest {
  @Autowired
  private RedisRepository repository;

  @Test
  void testSave() {
    QuizzSessionRedis a = new QuizzSessionRedis();
    a.setId("abc");
    a.setCreationTime(OffsetDateTime.now(ZoneOffset.UTC));
    QuizzSessionRedis b = repository.save(a);
    assertTrue(a.equals(b));
    repository.delete(b);
    Optional<QuizzSessionRedis> c = repository.findById(a.getId());
    assertFalse(c.isPresent());
  }
}
