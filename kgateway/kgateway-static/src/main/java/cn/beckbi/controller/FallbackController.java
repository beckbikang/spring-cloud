package cn.beckbi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-21 21:25
 */
@Slf4j
@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("")
    public Mono<String> fallback(){
        return  Mono.fromSupplier(()-> "fallback");
    }
}
