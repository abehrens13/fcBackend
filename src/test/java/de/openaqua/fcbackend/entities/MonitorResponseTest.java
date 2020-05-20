package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.openaqua.fcbackend.model.MonitorResponse;
import de.openaqua.fcbackend.model.MonitorStatus;

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
