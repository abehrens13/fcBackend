package de.openaqua.fcbackend.redis;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RedisSessionRepository extends PagingAndSortingRepository<RedisSession, String> {
}
