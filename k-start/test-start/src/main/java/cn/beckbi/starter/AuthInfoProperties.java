package cn.beckbi.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-06-27 19:38
 */

@Data
@ConfigurationProperties("spring.data.auth.info")
public class AuthInfoProperties {

    private String user;
    private String token;
}
