package cn.beckbi.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-06-27 19:38
 */
@Configuration
@EnableConfigurationProperties(AuthInfoProperties.class)
public class AuthInfoAutoConfigure {

    @Bean
    @ConditionalOnProperty(prefix = "spring.data.auth.info", value = "enabled", havingValue = "true")
    public AuthInfo authInfo(AuthInfoProperties authInfoProperties){
        return new AuthInfo(authInfoProperties);
    }
}
