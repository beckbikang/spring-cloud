package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-13 08:02
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulStaticGateway {

    public static void main(String[] args) {
        SpringApplication.run(ZuulStaticGateway.class, args);
    }
}
