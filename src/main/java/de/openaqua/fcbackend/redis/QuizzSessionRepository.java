package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.CrudRepository;

import de.openaqua.fcbackend.entities.QuizzSession;

//TODO: Redis forgets values from QuizzSession. Whats going on???
public interface QuizzSessionRepository extends CrudRepository<QuizzSession, String> {
}
