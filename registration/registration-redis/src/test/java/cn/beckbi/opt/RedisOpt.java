package cn.beckbi.opt;

import cn.beckbi.TestApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-13 16:15
 */
@Slf4j
public class RedisOpt extends TestApp {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void baseOpt() {
        log.info("test");
        redisTemplate.opsForValue().set("abc", "123111");
    }
}
