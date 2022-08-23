package cn.beckbi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-18 22:59
 */
@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    public Mono<String> hello(){
        final String key = "aaaaa";
        redisTemplate.opsForValue().set(key, "1111111");
        log.info("value:"+redisTemplate.opsForValue().get(key));

        return  Mono.fromSupplier(()-> "ok");
    }
}
