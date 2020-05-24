package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.CrudRepository;

import de.openaqua.fcbackend.entities.Mein;

public interface MeinRepository extends CrudRepository<Mein, String> {
}
