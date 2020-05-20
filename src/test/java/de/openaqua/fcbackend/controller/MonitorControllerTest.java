package de.openaqua.fcbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.openaqua.fcbackend.model.MonitorResponse;
import de.openaqua.fcbackend.model.MonitorStatus;

@SpringBootTest()
class MonitorControllerTest {
  @Autowired
  private MonitorController controller;

  @Test
  void testIndex() {
    assertNotNull(controller);
    MonitorResponse r = controller.getMonitor().getBody();
    assertNotNull(r);
    assertEquals(r.getStatusSystem(), MonitorStatus.OK);
    assertEquals(r.getStatusRedis(), MonitorStatus.OK);
    assertEquals(r.getStatusOverall(), MonitorStatus.OK);
    assertFalse(r.getHostname().isEmpty());
    assertFalse(r.getCurrentTime().toString().isEmpty());
    assertFalse(r.getInetAddress().isEmpty());
  }

}
