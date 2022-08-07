package cn.beckbi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-02-09 23:04
 */
@RestController
public class UserController {

    private final ObjectMapper mapper =new ObjectMapper();



    @GetMapping("/user/{uid}")
    @HystrixCommand(fallbackMethod = "defaultCall",
    commandProperties = {
            @HystrixProperty(
                    name = "execution.isolation.strategy",
                    value = "THREAD"
            )
    })
    public String info(@PathVariable long uid) throws JsonProcessingException {

        @Data
        class IdData {
            private String id;
            private Long uid;
        }

        IdData idData = new IdData();
        idData.setUid(uid);
        idData.setId("123");
        return mapper.writeValueAsString(idData);
    }

    public String defaultCall(long uid) {
        return "failed:"+uid;
    }


}
