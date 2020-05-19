package de.openaqua.fcbackend.entities;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.swagger.model.MonitorResponse;
import io.swagger.model.MonitorStatus;

class MonitorResponseTest {


	@Test
	void testGetterSetter() {
		MonitorResponse r = new MonitorResponse();
		r.setStatusRedis(MonitorStatus.FAILURE);
		r.setStatusSystem(MonitorStatus.FAILURE);
		assertTrue(r.getStatusRedis() == MonitorStatus.FAILURE);
		assertTrue(r.getStatusSystem() == MonitorStatus.FAILURE);
	}
}
