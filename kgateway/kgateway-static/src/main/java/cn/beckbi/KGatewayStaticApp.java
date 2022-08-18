package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: spring-cloud
 * @description: KGatewayStaticApp
 * @author: bikang
 * @create: 2022-08-15 23:10
 */
@EnableDiscoveryClient
@SpringBootApplication
public class KGatewayStaticApp {
    public static void main(String[] args) {
        SpringApplication.run(KGatewayStaticApp.class, args);
    }
}
