package de.openaqua.fcbackend.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import de.openaqua.fcbackend.model.IQuizzSession;

@RedisHash("QuizzSession")
public class QuizzSession extends IQuizzSession implements Serializable {
  private static final long serialVersionUID = -7223865767016246635L;

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
