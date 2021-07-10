package cn.beckbi.fallback;

import cn.beckbi.service.HelloFeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-10 18:27
 */
@Component
public class HelloFeignServiceFallback implements HelloFeignService {

    @Override
    public String hello(@RequestParam("source") String queryStr){
        return "hello failed";
    }
}
