package de.openaqua.fcbackend.entities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import de.openaqua.fcbackend.model.IQuizzSession;

@RedisHash
public class QuizzSession extends IQuizzSession {
  public QuizzSession() {
    super();
    this.setId(UUID.randomUUID().toString());
    this.setCreationTime(OffsetDateTime.now(ZoneOffset.UTC));
  }

  @Override
  public boolean equals(java.lang.Object o) {
    return super.equals(o);
  }
}
