package cn.beckbi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description: ad
 * @author: bikang
 * @create: 2021-06-29 23:35
 */
@RestController
public class Ad {

    @GetMapping("ad/list")
    public String list(){
        return "list";
    }
}
