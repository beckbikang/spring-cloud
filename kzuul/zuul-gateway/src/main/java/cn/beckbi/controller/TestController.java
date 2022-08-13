package cn.beckbi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-13 08:41
 */
@RestController
public class TestController {

    @GetMapping("/abc/{id}")
    public String info(@PathVariable String id) {
       return "ok:"+id;
    }
}
