package de.openaqua.fcbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

import de.openaqua.fcbackend.entities.QuizzSession;

@SpringBootTest()
class QuizzSessionControllerTest {
  @Autowired
  private QuizzSessionController controller;

  @Test
  void testNewSession() {

    // get new session
    ResponseEntity<QuizzSession> r1 = controller.newSession();
    assertTrue(r1.getStatusCodeValue() == 200);
    QuizzSession s = r1.getBody();
    assertFalse(s.getId().isEmpty());

    // patch session
    ResponseEntity<QuizzSession> r2 = controller.patchSession(s.getId());
    assertTrue(r2.getStatusCodeValue() == 200);
    assertTrue(r1.getBody().getId().equals(r2.getBody().getId()));

    // delete session
    ResponseEntity<String> r3 = controller.deleteSession(s.getId());
    assertTrue(r3.getStatusCodeValue() == 200);
    assertTrue(r1.getBody().getId().equals(r3.getBody()));

    // patch illegal session
    Assertions.assertThrows(ResourceAccessException.class, () -> {
      controller.patchSession(s.getId());
    });

    // delete illegal session
    Assertions.assertThrows(ResourceAccessException.class, () -> {
      controller.deleteSession(s.getId());
    });

  }
}
