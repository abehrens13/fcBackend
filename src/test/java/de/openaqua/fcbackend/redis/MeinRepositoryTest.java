package de.openaqua.fcbackend.redis;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import de.openaqua.fcbackend.controller.QuestionController;
import de.openaqua.fcbackend.entities.Mein;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeinRepositoryTest {
  private static final Logger LOG = LoggerFactory.getLogger(QuestionController.class);
  @LocalServerPort
  int randomServerPort;

  @Autowired
  MeinRepository repository;

  @Test
  void testAEnvironment() {
    log.info("testEnvironment");
    assertNotNull(repository);
  }

  @Test
  void testSave() {
    log.info("testSave");
    // prepare new object
    Mein a = new Mein();
    a.setCurrentTimeStamp(OffsetDateTime.now(ZoneOffset.UTC));
    a.setId(UUID.randomUUID().toString());
    a.setTxt1("text1");
    a.setTxt2("text2");
    LOG.debug("1: " + a.toString());

    Mein b = repository.save(a);
    LOG.debug("2: " + b.toString());

    Mein c = repository.findById(a.getId()).get();
    LOG.error("=========================redis forgets the time");
    LOG.error("5: " + c.toString());
    LOG.error("=========================redis forgets the time");
    assertNull(c.getCurrentTimeStamp());
    assertTrue(a.getId().equals(b.getId()));
  }

}
