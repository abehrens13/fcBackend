package de.openaqua.fcbackend.main;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.openaqua.fcbackend.SerialGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SerialGeneratorTest {
  @Autowired
  private SerialGenerator generator;

  @Test
  void testSerialGeneratorAutowired() {
    assertTrue(generator != null);
    assertFalse(generator.getPrefix().isEmpty());
    assertTrue(generator.getPrefix().equals("DEF"));
    assertTrue(generator.getNumber() == SerialGenerator.DEFAULT_STARTVALUE);
  }

  @Test
  void testSerialGenerator() {
    SerialGenerator g = new SerialGenerator();
    assertFalse(g.getPrefix().isEmpty());
    assertTrue(g.getPrefix().equals("DEF"));
    assertTrue(g.getNumber() == SerialGenerator.DEFAULT_STARTVALUE);

  }

  @Test
  void testSerialGeneratorStringInt() {
    SerialGenerator g = new SerialGenerator("ABC", 123);
    assertFalse(g.getPrefix().isEmpty());
    assertTrue(g.getPrefix().equals("ABC"));
    assertTrue(g.getNumber() == 123);
    assertTrue(g.getNumber() != SerialGenerator.DEFAULT_STARTVALUE);
  }

  @Test
  void testSerialGeneratorNext() {
    SerialGenerator g = new SerialGenerator("ABC", 1);
    assertFalse(g.getPrefix().isEmpty());
    assertTrue(g.getPrefix().equals("ABC"));
    assertTrue(g.getNumber() == 1);
    String a1 = g.getNext();
    String a2 = g.getNext();
    assertTrue(g.getNumber() == 3);
    assertFalse(a1.equals(a2));
    assertTrue(g.getNumber() != SerialGenerator.DEFAULT_STARTVALUE);
  }

  @Test
  void testToString() {
    SerialGenerator g = new SerialGenerator();
    assertTrue(g.toString() != null);
    assertFalse(g.toString().isEmpty());
  }

  @Test
  void testGetNext() {
    SerialGenerator g = new SerialGenerator();
    assertTrue(g.getNumber() == SerialGenerator.DEFAULT_STARTVALUE);
    int i = 123456;
    assertTrue(i != SerialGenerator.DEFAULT_STARTVALUE);
    g.setNumber(i);
    assertTrue(g.getNumber() != SerialGenerator.DEFAULT_STARTVALUE);
    assertTrue(g.getNumber() == i);

  }

  @Test
  void testSetGetNumber() {
    SerialGenerator g = new SerialGenerator();
    assertTrue(g.getPrefix().equals("DEF"));
    g.setPrefix("ABC");
    assertTrue(g.getPrefix().equals("ABC"));
  }

  @Test
  void testSetGetPrefix() {
    SerialGenerator g = new SerialGenerator();
    assertTrue(g.getPrefix().equals("DEF"));
    g.setPrefix("ABC");
    assertTrue(g.getPrefix().equals("ABC"));
  }

}