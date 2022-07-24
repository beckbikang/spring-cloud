package cn.beckbi.user.front.config.feign.config;

import cn.beckbi.user.front.config.feign.auth.FeignAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-24 11:56
 */
@Configuration
public class FeignConfiguration {
    /**
     * 日志级别
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("tom", "123456");
    }

    @Bean
    public FeignAuthInterceptor feignBasicAuthRequestInterceptor() {
        return new FeignAuthInterceptor();
    }

}
