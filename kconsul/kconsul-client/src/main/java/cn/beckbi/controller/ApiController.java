package cn.beckbi.controller;


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

    @RequestMapping("/ok")
    public String ok() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("zuul-gateway-static");
        log.info("url:"+serviceInstance.getUri());
        log.info("url:"+serviceInstance.getServiceId());

        String url = serviceInstance.getUri().toString()+"/health/status";
        log.info("realUrl:"+url);
        return restTemplate.getForObject(
                url, String.class
        );
    }


}
