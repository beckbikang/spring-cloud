package cn.beckbi.service.impl;

import cn.beckbi.build.id.IdMaker;
import cn.beckbi.service.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-02 21:22
 */
@Slf4j
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    @Resource
    private IdMaker idMaker;

    @Resource(name = "masterRedisTemplate")
    private RedisTemplate<String, String>  masterRedisTemplate;


    @Resource(name = "slaveRedisTemplate")
    private RedisTemplate<String, String>  slaveRedisTemplate;



    /**
     * get id
     * @Param uid integer
     * @return String
     */
    @Override
    public String masterGetId(Long uid) {
        String cacheKey = "master:uid:"+uid;
        String id = masterRedisTemplate.opsForValue().get(cacheKey);
        log.info("masterGetId:redis:{},{}",cacheKey,id);
        if (Objects.isNull(id)) {
            id = idMaker.getId();
            masterRedisTemplate.opsForValue().set(cacheKey, id, 5, TimeUnit.SECONDS);
        }
        return id;
    }

    /**
     * get id
     * @Param uid integer
     * @return String
     */
    @Override
    public String slaveGetId(Long uid) {
        String cacheKey = "slave:uid:"+uid;
        String id = slaveRedisTemplate.opsForValue().get(cacheKey);
        log.info("slaveGetId:redis:{},{}",cacheKey,id);
        if (Objects.isNull(id)) {
            id = idMaker.getId();
            slaveRedisTemplate.opsForValue().set(cacheKey, id, 5, TimeUnit.SECONDS);
        }
        return id;
    }
}
