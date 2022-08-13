package cn.beckbi.config;

import cn.beckbi.filter.ErrorFilter;
import cn.beckbi.filter.SpecialPathFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-13 11:35
 */
@Configuration
public class ZuulFilterConfig {

    @Bean
    public SpecialPathFilter specialPathFilter() {
        return new SpecialPathFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
}
