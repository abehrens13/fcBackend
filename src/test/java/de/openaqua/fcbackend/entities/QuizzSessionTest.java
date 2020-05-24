package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class QuizzSessionTest {

  @Test
  void testConstructor() throws InterruptedException {
    QuizzSession s = new QuizzSession();
    OffsetDateTime create = OffsetDateTime.parse(s.getCreationTime());
    TimeUnit.SECONDS.sleep(2);
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    long diff = now.toEpochSecond() - create.toEpochSecond();
    log.debug("diff {}", diff);
    assertTrue(diff > 0 && diff < 5);

    assertNotNull(s);
    assertNotNull(s.getId());
    assertNotNull(s.getCreationTime());
    assertNotNull(s.toString());
    assertFalse(s.getId().isEmpty());
    assertTrue(s.getId().length() > 10);
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
    TimeUnit.SECONDS.sleep(1);
    String nowS = OffsetDateTime.now(ZoneOffset.UTC).toString();
    assertFalse(s.getCreationTime().equals(nowS));
    s.setCreationTime(nowS);
    assertTrue(s.getCreationTime().equals(nowS));

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
