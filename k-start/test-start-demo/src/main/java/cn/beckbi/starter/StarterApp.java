package cn.beckbi.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-06-27 20:07
 */
@EnableAuthInfo
@SpringBootApplication
public class StarterApp {

    public static void main(String[] args) {
        SpringApplication.run(StarterApp.class, args);
    }
}
