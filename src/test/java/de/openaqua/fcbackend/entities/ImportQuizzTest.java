package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ImportQuizzTest {

  @Test
  void testQuizz() {
    ImportQuizz q = new ImportQuizz();
    assertEquals(q.getDescription(), "");
    q.setDescription("abc");
    assertEquals("abc", q.getDescription());
  }

}
