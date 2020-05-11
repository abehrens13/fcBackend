package de.openaqua.fcbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.context.annotation.Bean;
import javax.servlet.Filter;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.strategy.DynamicSegmentNamingStrategy;

@Configuration
public class WebConfig {

	@Bean
	public Filter TracingFilter() {
		return new AWSXRayServletFilter(new DynamicSegmentNamingStrategy("FcBackend", "openaqua.de"));
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		// use the default (localhost, 6379)
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
