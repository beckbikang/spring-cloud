package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @program: spring-cloud
 * @description: ZuulGatewayDynamic
 * @author: bikang
 * @create: 2022-08-14 12:59
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayDynamic {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayDynamic.class, args);
    }
}
