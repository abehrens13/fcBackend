package de.openaqua.fcbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import de.openaqua.fcbackend.entities.QuizzSessionRedis;

public interface QuizzSessionRepository extends CrudRepository<QuizzSessionRedis, String> {
}
