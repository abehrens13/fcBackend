package de.openaqua.fcbackend.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.openaqua.fcbackend.SerialGenerator;
import de.openaqua.fcbackend.entities.MonitorResponse;
import de.openaqua.fcbackend.entities.MonitorStatus;
import de.openaqua.fcbackend.redis.SessionRepository;
import de.openaqua.fcbackend.redis.RedisSession;

@RestController
@RequestMapping(path = "/monitor")
public class MonitorController {
  private static final Logger LOG = LoggerFactory.getLogger(MonitorController.class);

  @Autowired
  private SessionRepository redis;

  @Autowired
  private SerialGenerator serialGenerator;

  private MonitorStatus checkRedis() {
    // Store and retrieve some data from redis to test redis
    try {
      if (serialGenerator == null) {
        throw new ServiceNotAvailable("serialGenerator is null");
      }
      String serial = serialGenerator.getNext();
      if (serial == null) {
        serial = "null-001";
      }
      RedisSession session = new RedisSession(serial);

      Optional<RedisSession> o = redis.findById(session.getId());
      if (o.isPresent()) {
        throw new RedisConnectionFailureException("Redis has a session object with which should not exists");
      }
      session = redis.save(session);
      Optional<RedisSession> o2 = redis.findById(session.getId());
      if (!o2.isPresent()) {
        throw new RedisConnectionFailureException("Redis has no session object with which should exists");
      }
      redis.delete(session);

    } catch (RedisConnectionFailureException | ServiceNotAvailable e) {
      LOG.error("Redis Exception: {}", e.getLocalizedMessage());
      return MonitorStatus.FAILURE;
    }

    return MonitorStatus.OK;
  }

  @GetMapping()
  public MonitorResponse index() {
    LOG.info("GET /");

    // create default response
    MonitorResponse response = new MonitorResponse();
    response.setStatusDatabase(MonitorStatus.UNKNOWN);
    response.setStatusSystem(MonitorStatus.OK);
    response.setStatusRedis(checkRedis());

    // return
    return response;
  }
}
