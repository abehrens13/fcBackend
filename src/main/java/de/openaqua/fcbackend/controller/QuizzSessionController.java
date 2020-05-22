package de.openaqua.fcbackend.controller;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.openaqua.fcbackend.api.SessionApi;
import de.openaqua.fcbackend.model.QuizzSession;
import de.openaqua.fcbackend.redis.QuizzSessionRedis;
import de.openaqua.fcbackend.redis.RedisRepository;
import io.swagger.annotations.ApiParam;

@Component
@RestController
public class QuizzSessionController implements SessionApi {

  @Autowired
  private RedisRepository repository;

  @Override
  public ResponseEntity<QuizzSession> getSession() {

    log.info("GET /session/new");
    QuizzSessionRedis s = new QuizzSessionRedis();
    s.setId(UUID.randomUUID().toString());
    s.setCreationTime(OffsetDateTime.now(ZoneOffset.UTC));
    QuizzSessionRedis a = repository.save(s);
    log.info("New Session startet: {}", a.getId());
    return ResponseEntity.ok(a);
  }

  @Override
  public ResponseEntity<Void> deleteSession(
      @ApiParam(value = "session String as created by get", required = true) @PathVariable("sessionId") String sessionId) {
    log.info("DELETE /session={}", sessionId);
    Optional<QuizzSessionRedis> c = repository.findById(sessionId);
    if (c.isPresent()) {
      repository.delete(c.get());
      log.info("session finished {}", sessionId);
    } else {
      log.warn("attemp to delete unknown session {}", sessionId);
      return new ResponseEntity<>(HttpStatus.GONE);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
