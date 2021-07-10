package cn.beckbi.controller;

import cn.beckbi.service.HelloFeignService;
import cn.beckbi.service.SimpleFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-10 18:06
 */
@RestController
public class Hello {

    @Resource
    HelloFeignService helloFeignService;

    @Resource
    SimpleFeignService simpleFeignService;

    @GetMapping("/hi")
    public String hi(){
        return helloFeignService.hello("http");
    }

    @GetMapping("/hi/eureka")
    public String hiEureka(){
        return simpleFeignService.hello("eureka");
    }


}
