package cn.beckbi.controller;


import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-10 18:06
 */
@RestController
public class Test {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hi(){
        return "c2";
    }


    @GetMapping("/hi2")
    public String hi2(){

        return restTemplate
                .getForObject("http://EUREKA-SERVER-CREATIVE/hello" , String.class);
    }




}
