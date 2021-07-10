package cn.beckbi.service;

import cn.beckbi.fallback.HelloFeginServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-10 18:05
 */
@FeignClient(name = "simple-fegin", url = "http://127.0.0.1:8763", fallback = HelloFeginServiceFallback.class)
public interface HelloFeginService {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello(@RequestParam("source") String queryStr);
}
