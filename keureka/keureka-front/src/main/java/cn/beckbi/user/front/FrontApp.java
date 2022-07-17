package cn.beckbi.user.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-17 23:10
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FrontApp {
    public static void main(String[] args) {
        SpringApplication.run(FrontApp.class, args);
    }
}
