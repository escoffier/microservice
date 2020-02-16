package microservice.cloudgateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

//import org.springframework.cloud.client.circuitbreaker.Customizer;
import java.time.Duration;

@Configuration
public class RoutesConfig {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

/*
    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes().route( p -> p
        .path("/v2/organizations/*")
        .filters(f -> f.addRequestHeader("myname", "robbie"))
        .uri("http://192.168.254.132:8080"))
        .build();
    }
*/

    @Bean
    KeyResolver userKeyResolver() {
        //return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
        return exchange -> Mono.just("nothing");
    }

//    @Bean
//    public RateLimiter inMemoryRateLimiter(){
//        return new InMemoryRateLimiter();
//    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
//                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                .build());
//    }

//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("circuitbreaker_route", r -> r.path("/consumingServiceEndpoint")
//                        .filters(f -> f.circuitBreaker(c -> c.name("myCircuitBreaker").fallbackUri("forward:/inCaseOfFailureUseThis"))
//                                .rewritePath("/consumingServiceEndpoint", "/backingServiceEndpoint")).uri("lb://backing-service:8088")
//                        .build();
//    }
}
