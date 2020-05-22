package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<QuizzSessionRedis, String> {
}
