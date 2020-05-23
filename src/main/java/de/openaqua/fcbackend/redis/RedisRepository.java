package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.CrudRepository;

import de.openaqua.fcbackend.entities.QuizzSession;

public interface RedisRepository extends CrudRepository<QuizzSession, String> {
}
