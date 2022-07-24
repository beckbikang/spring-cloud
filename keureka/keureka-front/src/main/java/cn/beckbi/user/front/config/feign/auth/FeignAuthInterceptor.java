package cn.beckbi.user.front.config.feign.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-24 11:58
 */
@Slf4j
public class FeignAuthInterceptor implements RequestInterceptor {

    public FeignAuthInterceptor() {

    }

    @Override
    public void apply(RequestTemplate template) {
        log.info("拦截器生效了");
    }
}
