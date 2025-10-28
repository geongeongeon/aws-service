package com.sparta.awsservice;

import static org.springframework.data.redis.serializer.RedisSerializationContext.*;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName("localhost");
		config.setPort(6379);
		config.setUsername("default");
		config.setPassword("0204");

		return new LettuceConnectionFactory(config);
	}

	@Bean
	public RedisCacheManager cacheManager(
		RedisConnectionFactory factory
	) {
		RedisCacheConfiguration config = RedisCacheConfiguration
			.defaultCacheConfig()
			.disableCachingNullValues()
			.entryTtl(Duration.ofSeconds(15))
			.computePrefixWith(CacheKeyPrefix.simple())
			.serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.java()))
			;

		return RedisCacheManager.builder(factory)
			.cacheDefaults(config)
			.build();
	}

}
