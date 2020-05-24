package de.openaqua.fcbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

  @Bean
  public JedisConnectionFactory redisConnectionFactory2() {
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("redis", 6379);
    return new JedisConnectionFactory(config);
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    RedisSerializer<String> stringSerializer = new StringRedisSerializer();
    JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
    template.setConnectionFactory(redisConnectionFactory2());
    template.setKeySerializer(stringSerializer);
    template.setHashKeySerializer(stringSerializer);
    template.setValueSerializer(jdkSerializationRedisSerializer);
    template.setHashValueSerializer(jdkSerializationRedisSerializer);
    template.setEnableTransactionSupport(true);
    template.afterPropertiesSet();
    return template;
  }

}
