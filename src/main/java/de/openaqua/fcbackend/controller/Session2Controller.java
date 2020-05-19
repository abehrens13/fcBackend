package de.openaqua.fcbackend.controller;

import io.swagger.model.QuizzSession2;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.api.Session2Api;

@RestController
public class Session2Controller implements Session2Api {
  private static final Logger LOG = LoggerFactory.getLogger(Session2Controller.class);

  @Override
  public ResponseEntity<QuizzSession2> session2Get() {
    LOG.info("GET /session2/new");

    LOG.info("A");
    if (getAcceptHeader().get().contains("application/json")) {
      LOG.info("B");
      QuizzSession2 r = new QuizzSession2();
      r.setId(UUID.randomUUID().toString());
      r.setCreationTime(OffsetDateTime.now(ZoneOffset.UTC));
      return ResponseEntity.ok(r);
    }
    LOG.info("C");
    LOG.info("E");
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }

}
