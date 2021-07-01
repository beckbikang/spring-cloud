package cn.beckbi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.netflix.discovery.EurekaClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-01 22:38
 */
@RestController
public class Creative {


    @Autowired
    private RestTemplate restTemplate;

    /**
     *
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     *
     */
    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("creative/tpl")
    public String getByRest(){
        return restTemplate.getForObject("http://localhost:8762/ad/list", String.class);
    }

    @GetMapping("creative/eureka")
    public String getByEurekaClient(){
        return restTemplate.getForObject("http://eureka-server-ad:8762/ad/list", String.class);
    }


    @GetMapping("discoveryClient")
    public Object getByDiscoveryClient(){
        return discoveryClient.getInstances("eureka-server-ad");
    }

    @GetMapping("eurekaClient")
    public Object getByDiscoveryClient2(){
        return eurekaClient.getInstancesByVipAddress("eureka-server-ad", false);
    }




    @GetMapping("creative/list")
    public String list(){
        return "list";
    }
}
