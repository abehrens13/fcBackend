package de.openaqua.fcbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.swagger.model.MonitorResponse;
import io.swagger.model.MonitorStatus;

@SpringBootTest()
class MonitorControllerTest {
  @Autowired
  private MonitorController controller;

  @Test
  void testIndex() {
    assertNotNull(controller);
    MonitorResponse r = controller.monitorGet().getBody();
    assertNotNull(r);
    assertEquals(r.getStatusSystem(), MonitorStatus.OK);
    assertEquals(r.getStatusRedis(), MonitorStatus.OK);
    assertEquals(r.getStatusOverall(), MonitorStatus.OK);
    assertFalse(r.getHostname().isEmpty());
    assertFalse(r.getCurrentTime().toString().isEmpty());
    assertFalse(r.getInetAddress().isEmpty());
  }

}
