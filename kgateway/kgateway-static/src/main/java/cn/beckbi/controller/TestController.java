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
 * @create: 2022-08-18 22:59
 */
@Slf4j
@RestController
@RequestMapping("/abcd111")
public class TestController {
    @GetMapping("/{id}")
    public Mono<String> hello(@PathVariable String id){
        return  Mono.fromSupplier(()-> "abcd:"+id);
    }
}
