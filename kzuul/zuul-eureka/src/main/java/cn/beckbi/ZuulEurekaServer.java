package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: spring-cloud
 * @description: eureka server
 * @author: bikang
 * @create: 2022-02-07 22:29
 */
@EnableEurekaServer
@SpringBootApplication
public class ZuulEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(ZuulEurekaServer.class, args);
    }
}
