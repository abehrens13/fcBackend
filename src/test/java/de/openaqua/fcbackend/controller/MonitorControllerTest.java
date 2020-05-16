package de.openaqua.fcbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.openaqua.fcbackend.entities.MonitorResponse;
import de.openaqua.fcbackend.entities.MonitorStatus;



class MonitorControllerTest {

	@Test
	void testIndex() {
		MonitorController c = new MonitorController();
		assertNotNull(c);
		MonitorResponse r = c.index();
		assertNotNull(r);
		assertEquals(r.getStatusDatabase(), MonitorStatus.UNKNOWN);
		assertEquals(r.getStatusSystem(), MonitorStatus.OK);
		assertEquals(r.getStatusRedis(), MonitorStatus.FAILURE);
	}

}
