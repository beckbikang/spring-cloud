package cn.beckbi.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-02-09 23:00
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulUserApplication.class, args);
    }
}

