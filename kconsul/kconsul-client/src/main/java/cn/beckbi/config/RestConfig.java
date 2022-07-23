package cn.beckbi.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-23 23:09
 */
@Configuration
public class RestConfig {

    
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
