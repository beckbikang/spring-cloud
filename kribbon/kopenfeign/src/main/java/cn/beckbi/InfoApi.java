package cn.beckbi;

import feign.Param;
import feign.RequestLine;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-24 13:38
 */
public interface InfoApi {

    @RequestLine("GET /user/{uid}")
    String info(@Param(value = "uid") long uid);

}
