package cn.beckbi.service;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-02 20:53
 */
public interface GuavaCacheService {
    /**
     * get id
     * @Param uid integer
     * @return String
     */
    String getId(Long uid);
}
