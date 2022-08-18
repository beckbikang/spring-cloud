package cn.beckbi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-23 22:55
 */
@RestController
public class UserController {

    private ObjectMapper mapper = new ObjectMapper();


    @RequestMapping("/info/{age}")
    public String info(@PathVariable(value= "age") int age)  throws JsonProcessingException {

        @Data
        class User {
            private String name;
            private int age;
        }
        User user = new User();
        user.setName("tom");
        user.setAge(age);
        return mapper.writeValueAsString(user);
    }
}
