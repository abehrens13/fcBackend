package fcbackend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.openaqua.fcbackend.entities.Quizz;

class TestQuizz {

	@Test
	void testQuizz() {
		Quizz q = new Quizz();
		assertEquals(q.getDescription(), "");
		q.setDescription("abc");
		assertEquals("abc", q.getDescription());
	}

}
