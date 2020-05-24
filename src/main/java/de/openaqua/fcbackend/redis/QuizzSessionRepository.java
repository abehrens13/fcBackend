package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.CrudRepository;

import de.openaqua.fcbackend.entities.QuizzSession;

public interface QuizzSessionRepository extends CrudRepository<QuizzSession, String> {
}
