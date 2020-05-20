package de.openaqua.fcbackend.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import de.openaqua.fcbackend.SerialGenerator;
import de.openaqua.fcbackend.model.MonitorResponse;
import de.openaqua.fcbackend.model.MonitorStatus;
import de.openaqua.fcbackend.redis.RedisSession;
import de.openaqua.fcbackend.redis.RedisSessionRepository;

@RestController
public class MonitorController implements de.openaqua.fcbackend.api.MonitorApi {

  @Autowired
  private RedisSessionRepository redis;

  @Autowired
  private SerialGenerator serialGenerator;

  private MonitorStatus checkRedis() {
    log.debug("checkRedis()");
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
      log.error("Redis Exception: {}", e.getLocalizedMessage());
      return MonitorStatus.FAILURE;
    }

    return MonitorStatus.OK;
  }

  @Override
  public ResponseEntity<MonitorResponse> getMonitor() {
    log.info("GET /");

    // create default response
    MonitorResponse response = new MonitorResponse();
    response.setCurrentTime(OffsetDateTime.now(ZoneOffset.UTC));
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      response.setInetAddress(inetAddress.getHostAddress());
      response.setHostname(inetAddress.getHostName());
    } catch (UnknownHostException e) {
      log.error("cannot resolve ip-address: {}", e.getLocalizedMessage());
      log.info("exception: ", e);
    }

    //set status
    response.setStatusOverall(MonitorStatus.UNKNOWN);
    response.setStatusSystem(MonitorStatus.OK);
    response.setStatusRedis(checkRedis());
    if (response.getStatusRedis().equals(MonitorStatus.OK)
        && response.getStatusSystem().equals(MonitorStatus.OK)) {
      response.setStatusOverall(MonitorStatus.OK);
    } else if (response.getStatusRedis().equals(MonitorStatus.FAILURE)
        || response.getStatusSystem().equals(MonitorStatus.FAILURE)) {
      response.setStatusOverall(MonitorStatus.FAILURE);
    }

    // return
    return ResponseEntity.ok(response);
  }
}
