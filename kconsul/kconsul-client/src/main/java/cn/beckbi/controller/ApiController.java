package cn.beckbi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-23 23:04
 */
@Slf4j
@RestController
public class ApiController {

    private ObjectMapper mapper = new ObjectMapper();


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/info")
    public String info() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("consul-server-producer");
        log.info("url:"+serviceInstance.getUri());
        log.info("url:"+serviceInstance.getServiceId());

        return restTemplate.getForObject(
                serviceInstance.getUri().toString()+"/info", String.class
        );
    }


}
