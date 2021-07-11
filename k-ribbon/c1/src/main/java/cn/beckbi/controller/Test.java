package cn.beckbi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-01 22:38
 */
@RestController
public class Test {




    @GetMapping("hello")
    public String list(){
        return "c1";
    }
}
