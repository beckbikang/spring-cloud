package cn.beckbi.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-21 20:21
 */
@Slf4j
@Configuration
public class GlobalFilterConfig {


    @Bean
    @Order(-2)
    public GlobalFilter filter1() {
        return (exchange, chain) -> {
            log.info("filter1 pre");
            return chain.filter(
                    exchange
            ).then(
                Mono.fromRunnable(()->{
                    log.info("filter1 post");
                })
            );
        };
    }

    @Bean
    @Order(0)
    public GlobalFilter filter2() {
        return (exchange, chain) -> {
            log.info("filter2 pre");
            return chain.filter(
                    exchange
            ).then(
                    Mono.fromRunnable(()->{
                        log.info("filter2 post");
                    })
            );
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter filter3() {
        return (exchange, chain) -> {
            log.info("filter3 pre");
            return chain.filter(
                    exchange
            ).then(
                    Mono.fromRunnable(()->{
                        log.info("filter3 post");
                    })
            );
        };
    }
}


