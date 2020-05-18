package de.openaqua.fcbackend.entities;

import java.time.OffsetDateTime;

import org.springframework.data.redis.core.RedisHash;

import de.openaqua.fcbackend.redis.RedisSession;

@RedisHash("RedisSession")

public class QuizzSession extends RedisSession {

  public QuizzSession() {
    super();
  }

  public QuizzSession(String id, OffsetDateTime creationTime) {
    super(id, creationTime);
  }

  public QuizzSession(String id) {
    super(id);
  }

  @Override
  public String toString() {
    return "QuizzSession [getId()=" + getId() + ", getCreationTime()=" + getCreationTime() + ", toString()="
        + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
  }
}
