package cn.beckbi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-23 22:55
 */
@Slf4j
@RestController
public class UserController {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private HttpServletRequest request;

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

        log.info("headers:"+ request.getHeader("from"));
        return mapper.writeValueAsString(user);
    }
}
