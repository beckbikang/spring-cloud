package cn.beckbi.user.front.controller;

import cn.beckbi.user.front.config.feign.api.UserInfoClientApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.discovery.EurekaClient;

import javax.annotation.Resource;

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

    @Resource
    private UserInfoClientApi userInfoClientApi;

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

    @GetMapping("/front3")
    public String front3() {
        return userInfoClientApi.info(3);
    }

}
