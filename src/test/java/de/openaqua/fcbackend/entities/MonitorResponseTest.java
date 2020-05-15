package de.openaqua.fcbackend.entities;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonitorResponseTest {

	@Test
	void testDefaults() {
		MonitorResponse r = new MonitorResponse();
		assertEquals(r.getStatusDatabase(), MonitorStatus.UNKNOWN);
		assertEquals(r.getStatusRedis(), MonitorStatus.UNKNOWN);
		assertEquals(r.getStatusSystem(), MonitorStatus.OK);
	}

	@Test
	void testGetterSetter() {
		MonitorResponse r = new MonitorResponse();
		r.setStatusDatabase(MonitorStatus.FAILURE);
		r.setStatusRedis(MonitorStatus.FAILURE);
		r.setStatusSystem(MonitorStatus.FAILURE);
		assertTrue(r.getStatusDatabase() == MonitorStatus.FAILURE);
		assertTrue(r.getStatusRedis() == MonitorStatus.FAILURE);
		assertTrue(r.getStatusSystem() == MonitorStatus.FAILURE);
	}
}
