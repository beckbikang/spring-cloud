package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-23 22:19
 */
@SpringBootApplication(scanBasePackages ={"cn.beckbi"})
@EnableDiscoveryClient
public class ConsulProducerServer {

    public static void main(String[] args) {
        SpringApplication.run(ConsulProducerServer.class, args);
    }
}
