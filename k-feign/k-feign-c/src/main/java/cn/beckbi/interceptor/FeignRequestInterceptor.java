package cn.beckbi.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-10 18:06
 */
@Slf4j
@Component
public class FeignRequestInterceptor  implements RequestInterceptor {


    /**
     * impl
     *
     * @param template RequestTemplate
     */
    @Override
    public void apply(RequestTemplate template) {
        log.info("interceptor "+ template.url());
    }
}
