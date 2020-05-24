package de.openaqua.fcbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import de.openaqua.fcbackend.entities.Mein;

@Configuration
public class RedisConfiguration {

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("redis", 6379);
    return new JedisConnectionFactory(config);
  }

  @Bean
  public RedisTemplate<String, Mein> redisTemplate() {
    RedisTemplate<String, Mein> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setEnableTransactionSupport(false);
    return redisTemplate;
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate() {
    StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory());
    stringRedisTemplate.setEnableTransactionSupport(false);
    return stringRedisTemplate;
  }
}
