package cn.beckbi.controller;

import cn.beckbi.service.HelloFeginService;
import cn.beckbi.service.SimpleFeginService;
import org.springframework.beans.factory.annotation.Autowired;
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
    HelloFeginService helloFeginService;

    @Resource
    SimpleFeginService simpleFeginService;

    @GetMapping("/hi")
    public String hi(){
        return helloFeginService.hello("http");
    }

    @GetMapping("/hi/eureka")
    public String hiEureka(){
        return simpleFeginService.hello("eureka");
    }


}
