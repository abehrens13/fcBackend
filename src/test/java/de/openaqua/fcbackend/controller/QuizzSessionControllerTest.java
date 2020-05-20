package de.openaqua.fcbackend.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import de.openaqua.fcbackend.model.QuizzSession;

@SpringBootTest()
class QuizzSessionControllerTest {
  @Autowired
  private QuizzSessionController controller;

  @Test
  void testNewSession() {
    assertFalse(1 == 2);

    // get new session
    ResponseEntity<QuizzSession> r1 = controller.getSession();
    assertTrue(r1.getStatusCodeValue() == 200);
    QuizzSession s = r1.getBody();
    assertFalse(s.getId().isEmpty());

    // delete session
    ResponseEntity<Void> r3 = controller.deleteSession(s.getId());
    assertTrue(r3.getStatusCodeValue() == 200);
    // delete illegal session
    ResponseEntity<Void> r4 = controller.deleteSession(s.getId());
    assertTrue(r4.getStatusCodeValue() == 410);

  }
}
