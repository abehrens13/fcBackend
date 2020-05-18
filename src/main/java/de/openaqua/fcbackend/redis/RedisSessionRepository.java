package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisSessionRepository extends CrudRepository<RedisSession, String> {
}
