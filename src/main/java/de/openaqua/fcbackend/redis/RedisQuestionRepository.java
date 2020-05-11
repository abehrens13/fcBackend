package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.openaqua.fcbackend.entities.Question;

@Repository
public interface RedisQuestionRepository extends CrudRepository<Question, String> {
}
