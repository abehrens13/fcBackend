package de.openaqua.fcbackend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.openaqua.fcbackend.api.QuizzApi;
import de.openaqua.fcbackend.entities.QuizzSession;
import de.openaqua.fcbackend.model.IQuizzSession;
import de.openaqua.fcbackend.redis.RedisRepository;
import io.swagger.annotations.ApiParam;

@Component
@RestController
public class QuizzSessionController implements QuizzApi {

  @Autowired
  private RedisRepository redisRepository;

  @Override
  public ResponseEntity<IQuizzSession> getSession() {
    log.info("GET /session/new");

    QuizzSession a = redisRepository.save(new QuizzSession());
    log.info("New Session startet: {}", a.getId());

    // return the session
    return ResponseEntity.ok(a);
  }

  @Override
  public ResponseEntity<Void> deleteSession(
      @ApiParam(value = "session String as created by get", required = true) @PathVariable("sessionId") String sessionId) {
    log.info("DELETE /session={}", sessionId);
    Optional<QuizzSession> c = redisRepository.findById(sessionId);
    if (c.isPresent()) {
      redisRepository.delete(c.get());
      log.info("session finished {}", sessionId);
    } else {
      log.warn("attemp to delete unknown session {}", sessionId);
      return new ResponseEntity<>(HttpStatus.GONE);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
