package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<RedisSession, String> {
}
