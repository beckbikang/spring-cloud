package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-23 23:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsulClientApp.class, args);
    }
}
