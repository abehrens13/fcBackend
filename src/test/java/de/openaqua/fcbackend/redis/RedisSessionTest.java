package de.openaqua.fcbackend.redis;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class RedisSessionTest {

  @Test
  void testSetGetId() {
    QuizzSessionRedis s = new QuizzSessionRedis();
    String abc = "abc";
    s.setId(abc);
    assertTrue(s.getId().equals(abc));
    s.setId("def");
    assertTrue(s.getId().equals("def"));
  }

  @Test
  void testSetGetCreationTime() throws InterruptedException {
    QuizzSessionRedis s = new QuizzSessionRedis();
    s.setCreationTime(OffsetDateTime.now(ZoneOffset.UTC));
    TimeUnit.SECONDS.sleep(1);
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    assertFalse(s.getCreationTime().equals(now));
    s.setCreationTime(now);
    assertTrue(s.getCreationTime().equals(now));

  }

  @Test
  void testToString() {
    QuizzSessionRedis s = new QuizzSessionRedis();
    s.setId("abc");
    System.out.print(s.toString());
    String ss = s.toString();
    assertFalse(ss == null);
    assertFalse(ss.isEmpty());
  }

}
