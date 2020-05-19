package de.openaqua.fcbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.QuizzSession;


public interface QuizzSessionRepository extends CrudRepository<QuizzSession, String> {
}
