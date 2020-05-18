package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class QuizzSessionTest {

  @Test
  void testSetGetId() {
    QuizzSession s = new QuizzSession();
    String abc = "abc";
    assertFalse(s.getId().equals(abc));
    s.setId(abc);
    assertTrue(s.getId().equals(abc));
  }

  @Test
  void testSetGetCreationTime() throws InterruptedException {
    QuizzSession s = new QuizzSession();
    TimeUnit.SECONDS.sleep(1);
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    assertFalse(s.getCreationTime().equals(now));
    s.setCreationTime(now);
    assertTrue(s.getCreationTime().equals(now));

  }

  @Test
  void testToString() {
    QuizzSession s = new QuizzSession();
    assertFalse(s.toString() == null);
    assertFalse(s.toString().isEmpty());
  }
}
