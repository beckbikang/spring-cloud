package cn.beckbi.service.impl;

import cn.beckbi.build.id.IdMaker;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-02 21:05
 */
@Order(100)
@Service
public class CacheLoader extends com.google.common.cache.CacheLoader<Long, String> {


    @Resource
    private IdMaker idMaker;

    @Override
    public String load(Long key) throws Exception {
        return idMaker.getId();
    }
}
