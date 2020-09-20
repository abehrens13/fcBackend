package de.openaqua.fcbackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import de.openaqua.fcbackend.FcBackendApplication;
import de.openaqua.fcbackend.entities.ImportQuestion;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FcBackendApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(QuestionController.class)
class QuestionControllerTest {

  @LocalServerPort
  private int port=0;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testEnvironment() {
    assertNotEquals(port, 0);
    assertNotNull(restTemplate);
  }

  @Test
  void testById() {
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
