package cn.beckbi.user.front.config.feign.api;

import cn.beckbi.user.front.config.feign.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: spring-cloud
 * @description: user client api
 * @author: bikang
 * @create: 2022-07-24 11:55
 */
@FeignClient(value="eureka-client-user", configuration= FeignConfiguration.class)
public interface UserInfoClientApi {

    @GetMapping("/user/{uid}")
    public String info(@PathVariable long uid);
}
