package de.openaqua.fcbackend.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.openaqua.fcbackend.FcBackendApplication;
import de.openaqua.fcbackend.entities.QuizzSession;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FcBackendApplication.class)
class QuizzSessionControllerTest {

  @LocalServerPort
  int randomServerPort;

  @Test
  public void testEnvironment() throws Exception {
    assertTrue(randomServerPort > 0);
  }

  private ResponseEntity<QuizzSession> getSession() throws URISyntaxException {
    RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:" + randomServerPort + "/quizz";
    URI url = new URI(baseUrl);
    return restTemplate.getForEntity(url, QuizzSession.class);
  }

  @Test
  public void testGetSession() throws Exception {
    ResponseEntity<QuizzSession> result = getSession();

    assertEquals(200, result.getStatusCodeValue());
    assertNotNull(result.getBody());
    QuizzSession session = result.getBody();

    assertNotNull(session.getId());
    assertNotNull(session.getCreationTime());
    assertTrue(!session.getId().isEmpty());
  }

  @Test
  void testDeleteSessionIllegal() throws URISyntaxException {
    RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:" + randomServerPort + "/quizz/abc";
    URI url = new URI(baseUrl);

    try {
      restTemplate.delete(url);
    } catch (RestClientException e) {
      assertTrue(e.getMessage().startsWith("410"));
    }
  }

  @Test()
  void testDeleteSessionLegal() throws URISyntaxException {
    ResponseEntity<QuizzSession> result = getSession();
    String session = result.getBody().getId();

    RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:" + randomServerPort + "/quizz/" + session;
    URI url = new URI(baseUrl);

    assertDoesNotThrow(() -> restTemplate.delete(url));
  }

}
