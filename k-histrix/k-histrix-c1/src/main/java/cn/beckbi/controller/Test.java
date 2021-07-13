package cn.beckbi.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-13 23:39
 */
@RestController
public class Test {


    @HystrixCommand(fallbackMethod="defaultUser")
    @GetMapping("/getUser")
    public String getUser(@RequestParam String username) throws Exception{
        if("tom".equals(username)){
            return "hi tom";
        }else{
            throw new Exception("error");
        }
    }

    /**
     * 出错则调用该方法返回友好错误
     * @param username
     * @return
     */
    public String defaultUser(String username) {
        return "用户不存在";
    }

}
