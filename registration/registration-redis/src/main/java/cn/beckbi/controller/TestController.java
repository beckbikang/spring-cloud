package cn.beckbi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-13 17:00
 */
@RestController
public class TestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Data
    public static class UserInfo {
        private String name;
        private Long uid;
    }

    @GetMapping("/set/{uid}")
    public String info(@PathVariable long uid) throws Exception{
        String key = "test#"+uid;

        UserInfo userInfo = new UserInfo();
        userInfo.setName(key);
        userInfo.setUid(uid);

        stringRedisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(userInfo));
        return stringRedisTemplate.opsForValue().get(key);
    }
}
