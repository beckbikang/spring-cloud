package cn.beckbi.controller;

import cn.beckbi.route.DynamicRouteLocator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-13 17:00
 */
@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private DynamicRouteLocator dynamicRouteLocator;

    @GetMapping("/setRoute")
    public String setRoute() throws Exception{
        String value = "[{\"id\":1,\"path\":\"/abc/**\",\"serviceId\":\"\",\"url\":\"http://www.example.com/\",\"stripPrefix\":false,\"retryable\":true,\"customSensitiveHeaders\":false},{\"id\":2,\"path\":\"/user/**\",\"serviceId\":\"zuul-user\",\"url\":\"\",\"stripPrefix\":false,\"retryable\":true,\"customSensitiveHeaders\":false}]";
        stringRedisTemplate.opsForValue().set(
                DynamicRouteLocator.getRouteKey(),
                value
        );
        return "ok";
    }

    @GetMapping("/refresh")
    public String refresh() throws Exception{
        dynamicRouteLocator.refresh();;
        return "ok";
    }



}
