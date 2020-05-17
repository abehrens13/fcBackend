package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuizzTest {

  @Test
  void testQuizz() {
    Quizz q = new Quizz();
    assertEquals(q.getDescription(), "");
    q.setDescription("abc");
    assertEquals("abc", q.getDescription());
  }

}
