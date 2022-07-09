package cn.beckbi.controller;


import cn.beckbi.build.id.IdMaker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-02-09 23:04
 */
@RestController
public class UserController {

    private final ObjectMapper mapper =new ObjectMapper();


    @Resource
    private IdMaker idMaker;

    @GetMapping("/user/{uid}")
    public String info(@PathVariable long uid) throws JsonProcessingException {

        @Data
        class IdData {
            private String id;
            private Long uid;
        }

        IdData idData = new IdData();
        idData.setUid(uid);
        idData.setId(idMaker.getId());
        return mapper.writeValueAsString(idData);
    }
}
