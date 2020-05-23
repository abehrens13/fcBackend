package de.openaqua.fcbackend.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import de.openaqua.fcbackend.FcBackendApplication;
import de.openaqua.fcbackend.entities.ImportQuestion;

@SpringBootTest(classes = FcBackendApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(QuestionController.class)
class QuestionControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testEnvirnonment() {
    assertNotNull(port);
    assertNotNull(restTemplate);
    assertTrue(port != 0);
  }

  @Test
  void testbyId() {
    ImportQuestion sample = this.restTemplate.getForObject("http://localhost:" + port + "/questions/random",
        ImportQuestion.class);
    assertFalse(sample.getAnswers().isEmpty());
    assertNotNull(sample.getDescription());
    assertNotNull(sample.getQuestionStr());
  }
  @Test
  void testRandom() {
    ImportQuestion sample = this.restTemplate.getForObject("http://localhost:" + port + "/questions/random",
        ImportQuestion.class);
    assertFalse(sample.getAnswers().isEmpty());
    assertNotNull(sample.getDescription());
    assertNotNull(sample.getQuestionStr());
  }
}