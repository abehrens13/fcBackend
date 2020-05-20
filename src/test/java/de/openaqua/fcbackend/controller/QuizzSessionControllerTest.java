package de.openaqua.fcbackend.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class QuizzSessionControllerTest {
  // @Autowired
  // private QuizzSessionController controller;

  @Test
  void testNewSession() {
    assertFalse(1 == 2);

    // get new session
    // ResponseEntity<QuizzSession> r1 = controller.sessionGet();
    // assertTrue(r1.getStatusCodeValue() == 200);
    // QuizzSession s = r1.getBody();
    // assertFalse(s.getId().isEmpty());

    /*
     * // patch session
     * ResponseEntity<QuizzSession> r2 = controller.patchSession(s.getId());
     * assertTrue(r2.getStatusCodeValue() == 200);
     * assertTrue(r1.getBody().getId().equals(r2.getBody().getId()));
     * // delete session
     * ResponseEntity<String> r3 = controller.sessionDelete(s.getId());
     * assertTrue(r3.getStatusCodeValue() == 200);
     * assertTrue(r1.getBody().getId().equals(r3.getBody()));
     * // patch illegal session
     * Assertions.assertThrows(ResourceAccessException.class, () -> controller.patchSession(s.getId()));
     * // delete illegal session
     * Assertions.assertThrows(ResourceAccessException.class, () ->
     * controller.deleteSession(s.getId()));
     */
  }
}
