package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.swagger.model.MonitorStatus;

class MonitorStatusTest {

	@Test
	void testMonitorStatus() {
    MonitorStatus s = MonitorStatus.OK;
		assertEquals(s.name(), "OK");

	}
}
