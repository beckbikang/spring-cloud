package cn.beckbi.config;

import cn.beckbi.route.DynamicRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-14 13:40
 */
@Configuration
public class ZuulConfig {

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    ZuulProperties zuulProperties;

    @Bean
    public DynamicRouteLocator customRouteLocator(){
        return new DynamicRouteLocator(serverProperties.getServlet().getPath(),zuulProperties);
    }

}
