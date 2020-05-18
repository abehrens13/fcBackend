package de.openaqua.fcbackend.controller;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import de.openaqua.fcbackend.entities.QuizzSession;
import de.openaqua.fcbackend.repositories.QuizzSessionRepository;

@RestController
@RequestMapping(path = "/session")
public class QuizzSessionController {
  private static final Logger LOG = LoggerFactory.getLogger(QuizzSessionController.class);

  @Autowired
  private QuizzSessionRepository repository;

  @GetMapping
  public ResponseEntity<QuizzSession> newSession() {
    LOG.info("GET /session/new");
    QuizzSession a = repository.save(new QuizzSession(UUID.randomUUID().toString()));
    LOG.info("New Session startet: {}", a);
    return ResponseEntity.ok(a);
  }

  @PatchMapping("{q}")
  public ResponseEntity<QuizzSession> patchSession(@PathVariable final String q) {
    LOG.info("PATCH /session={}", q);

    Optional<QuizzSession> c = repository.findById(q);
    if (c.isPresent()) {
      LOG.info("session {} found, can be patched ", q);
      // TODO: Fill out later..
    } else {
      LOG.warn("attemp to patch unknown session {}", q);
      throw new ResourceAccessException("Resource Not found: " + q);
    }
    return ResponseEntity.ok(c.get());
  }

  @DeleteMapping("{q}")
  public ResponseEntity<String> deleteSession(@PathVariable final String q) {
    LOG.info("DELETE /session={}", q);

    Optional<QuizzSession> c = repository.findById(q);
    if (c.isPresent()) {
      repository.delete(c.get());
      LOG.info("session finished {}", q);
    } else {
      LOG.warn("attemp to delete unknown session {}", q);
      throw new ResourceAccessException("Resource Not found: " + q);
    }
    return ResponseEntity.ok(q);
  }

}
