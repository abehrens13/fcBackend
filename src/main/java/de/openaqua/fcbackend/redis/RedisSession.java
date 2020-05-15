package de.openaqua.fcbackend.redis;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("RedisSession")
public class RedisSession {
	private @Id String id;
	private OffsetDateTime creationTime;

	public RedisSession(String id, OffsetDateTime creationTime) {
		super();
		this.id = id;
		this.creationTime = creationTime;
	}

	public RedisSession(String id) {
		super();
		this.id = id;
		this.creationTime = OffsetDateTime.now(ZoneOffset.UTC);
	}

	public RedisSession() {
		super();
		this.id = "";
		this.creationTime = OffsetDateTime.now(ZoneOffset.UTC);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OffsetDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(OffsetDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", creationTime=" + creationTime + "]";
	}

}
