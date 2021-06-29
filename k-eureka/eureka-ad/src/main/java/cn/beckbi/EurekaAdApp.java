package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: spring-cloud
 * @description: app
 * @author: bikang
 * @create: 2021-06-29 23:35
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaAdApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaAdApp.class, args);
    }
}
