package br.com.fiap.tech.challenge.adapter.driven.redis.config;

import br.com.fiap.tech.challenge.adapter.driven.redis.converter.ReadingDocumentConverter;
import br.com.fiap.tech.challenge.adapter.driven.redis.converter.ReadingEmailRegistrationConverter;
import br.com.fiap.tech.challenge.adapter.driven.redis.converter.WritingDocumentConverter;
import br.com.fiap.tech.challenge.adapter.driven.redis.converter.WritingEmailRegistrationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.Duration;
import java.util.Arrays;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.core.RedisKeyValueAdapter.EnableKeyspaceEvents.OFF;

@Configuration
@ComponentScan("br.com.fiap.tech.challenge.adapter.driven.redis")
@EnableRedisRepositories(basePackages = "br.com.fiap.tech.challenge.adapter.driven.redis.repository", enableKeyspaceEvents = OFF)
public class RedisConfiguration {

    public static final String CACHE_REDIS_TTL = "spring.data.redis.time-to-live";
    private static final int TTL_ONE_DAY_MILLIS = 86400000;

    @Bean
    @Primary
    public RedisCacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory, Environment env) {
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(lettuceConnectionFactory)
                .cacheDefaults(defaultCacheConfig(getClass().getClassLoader())
                        .entryTtl(Duration.ofMillis(env.getProperty(CACHE_REDIS_TTL, Integer.class, TTL_ONE_DAY_MILLIS))))
                .build();
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(
                new ReadingDocumentConverter(),
                new WritingDocumentConverter(),
                new ReadingEmailRegistrationConverter(),
                new WritingEmailRegistrationConverter()
        ));
    }
}