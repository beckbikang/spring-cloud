package cn.beckbi.service;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-02 20:53
 */
public interface RedisCacheService {
    /**
     * get id
     * @Param uid integer
     * @return String
     */
    String masterGetId(Long uid);

    /**
     * get id
     * @Param uid integer
     * @return String
     */
    String slaveGetId(Long uid);
}
