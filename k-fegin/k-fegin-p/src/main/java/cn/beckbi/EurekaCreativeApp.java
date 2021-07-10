package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-01 22:38
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaCreativeApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCreativeApp.class, args);
    }
}

