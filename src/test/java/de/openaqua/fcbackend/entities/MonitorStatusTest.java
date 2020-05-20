package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.openaqua.fcbackend.model.MonitorStatus;

class MonitorStatusTest {

  @Test
  void testMonitorStatus() {
    MonitorStatus s = MonitorStatus.OK;
    assertEquals(s.name(), "OK");

  }
}
