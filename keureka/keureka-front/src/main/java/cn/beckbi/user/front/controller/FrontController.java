package cn.beckbi.user.front.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.discovery.EurekaClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-17 23:14
 */
@RestController
public class FrontController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private EurekaClient eurekaClient;


    @GetMapping("/info1")
    public Object info1() {
        return eurekaClient.getInstancesByVipAddress("eureka-client-user", false);
    }


    @GetMapping("/info2")
    public Object info2() {
        return discoveryClient.getInstances("eureka-client-user");
    }


    @GetMapping("/front1")
    public String front1() {
        return restTemplate.getForObject(
                "http://localhost:7010/user/1", String.class);
    }

    @GetMapping("/front2")
    public String front2() {
        return restTemplate.getForObject(
                "http://eureka-client-user/user/2", String.class);
    }

}
