package de.openaqua.fcbackend.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.openaqua.fcbackend.model.MonitorResponse;
import de.openaqua.fcbackend.model.MonitorStatus;


@RunWith(SpringRunner.class)
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
