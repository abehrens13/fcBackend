package de.openaqua.fcbackend.redis;

import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class RedisSessionTest {

	@Test
	void testSetGetId() {
		RedisSession s = new RedisSession();
		String abc = "abc";
		assertFalse(s.getId().equals(abc));
		s.setId(abc);
		assertTrue(s.getId().equals(abc));
	}

	@Test
	void testSetGetCreationTime() {
		RedisSession s = new RedisSession();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
		assertFalse(s.getCreationTime().equals(now));
		s.setCreationTime(now);
		assertTrue(s.getCreationTime().equals(now));

	}

	@Test
	void testToString() {
		RedisSession s = new RedisSession();
		assertFalse(s.toString() == null);
		assertFalse(s.toString().isEmpty());
	}

}
