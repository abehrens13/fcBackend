package de.openaqua.fcbackend.entities;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.ToString;

@RedisHash("Mein")
@ToString
public @Data class Mein {

  private @Id String id;
  private OffsetDateTime currentTimeStamp;
  private String txt1;
  private String txt2;

}
