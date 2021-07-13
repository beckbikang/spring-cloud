package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-13 23:24
 */
@EnableHystrix
@SpringBootApplication
@EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
