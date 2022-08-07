package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-07 12:06
 */

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class KhystrixSpringApp {


    public static void main(String[] args) {
        SpringApplication.run(KhystrixSpringApp.class, args);
    }

}
