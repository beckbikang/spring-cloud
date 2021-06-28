package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 *
 * @program: spring-cloud
 * @description: eureka server
 * @author: bikang
 * @create: 2021-06-28 22:49
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApp {

    public static void main(String[] args){
        SpringApplication.run(EurekaServerApp.class, args);
    }

}
