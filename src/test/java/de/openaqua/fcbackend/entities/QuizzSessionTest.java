package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class QuizzSessionTest {

  @Test
  void testConstructor() {
    QuizzSession s = new QuizzSession();
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    s.creationTime(now);
    long diff = now.toEpochSecond() - s.getCreationTime().toEpochSecond(); // should be less than 2 sec
    assertNotNull(s);
    assertNotNull(s.getId());
    assertNotNull(s.getCreationTime());
    assertNotNull(s.toString());
    assertFalse(s.getId().isEmpty());
    assertTrue(s.getId().length() > 10);
    assertTrue(diff >= 0);
    assertTrue(diff <= 2);
  }

  @Test
  void testSetGetId() {
    QuizzSession s = new QuizzSession();
    s.setId("def");
    String abc = "abc";
    assertFalse(s.getId().equals(abc));
    s.setId(abc);
    assertTrue(s.getId().equals(abc));
  }

  @Test
  void testSetGetCreationTime() throws InterruptedException {
    QuizzSession s = new QuizzSession();
    s.setCreationTime(OffsetDateTime.now(ZoneOffset.UTC));
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

  @Test
  void testEquals() {
    QuizzSession s = new QuizzSession();
    QuizzSession b = new QuizzSession();
    assertFalse(s.equals(b));
  }
}
