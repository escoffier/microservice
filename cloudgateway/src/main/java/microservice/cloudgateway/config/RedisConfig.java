package microservice.cloudgateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;

@Configuration
public class RedisConfig {

    private static final RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration()
            .master("mymaster")
            .sentinel("192.168.254.132", 26379)
            .sentinel("192.168.254.132", 27379)
            .sentinel("192.168.254.132", 28379);

//    @Bean("redisConnectionFactory")
//    ReactiveRedisConnectionFactory redisConnectionFactory() {
//        ReactiveRedisConnectionFactory connectionFactory = new LettuceConnectionFactory(sentinelConfig(),
//                  LettuceClientConfiguration.defaultConfiguration());
//          return connectionFactory;
//    }

//    @Bean
//    ReactiveRedisTemplate<String, String> reactiveRedisTemplate( ReactiveRedisConnectionFactory redisConnectionFactory){
//        RedisSerializationContext.RedisSerializationContextBuilder<String, String> builder =
//                RedisSerializationContext.newSerializationContext(new StringRedisSerializer()) ;
//        RedisSerializationContext<String, String> serializationContext;
//        serializationContext = builder.build();
//        return new ReactiveRedisTemplate<String, String>(redisConnectionFactory, serializationContext);
//    }

    @Bean
    public RedisSentinelConfiguration sentinelConfig() {
        return sentinelConfiguration;
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .build());
    }
}
