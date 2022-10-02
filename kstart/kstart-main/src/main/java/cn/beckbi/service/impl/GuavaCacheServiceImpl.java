package cn.beckbi.service.impl;


import cn.beckbi.service.GuavaCacheService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-02 20:53
 */
@Slf4j
@Service
public class GuavaCacheServiceImpl  implements GuavaCacheService {


    @Autowired
    private CacheLoader cacheLoader;


    private LoadingCache<Long,String> cache = null;


    @PostConstruct
    public void initCache() {
        cache = CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(1000)
                //是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
                .recordStats()
                //设置写缓存后n秒钟过期
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .removalListener(notification -> {
                    log.info(notification.getKey() + " " + notification.getValue() + " 被移除,原因:" + notification.getCause());
                })
                .build(cacheLoader);
    }


    /**
     * get id
     * @return String
     */
    @Override
    public  String getId(Long uid) {
        try {
            return cache.get(uid);
        }catch (ExecutionException ex) {
            return "";
        }
    }

}
