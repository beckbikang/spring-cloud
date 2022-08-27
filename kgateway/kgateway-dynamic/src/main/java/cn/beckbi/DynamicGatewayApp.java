package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-22 07:43
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DynamicGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(DynamicGatewayApp.class, args);
    }
}
