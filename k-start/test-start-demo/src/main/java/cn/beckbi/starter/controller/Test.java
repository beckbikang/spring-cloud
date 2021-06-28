package cn.beckbi.starter.controller;

import cn.beckbi.starter.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description: test
 * @author: bikang
 * @create: 2021-06-27 20:05
 */
@RestController
public class Test {

    @Autowired
    private AuthInfo authInfo;

    @GetMapping("/tokenInfo")
    public String tokenInfo() {
        return "user:"+authInfo.getUser() + " password:"+ authInfo.getToken();
    }

}
