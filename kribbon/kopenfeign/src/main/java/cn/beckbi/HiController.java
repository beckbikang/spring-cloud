package cn.beckbi;

import feign.Feign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-24 13:37
 */
@RestController
public class HiController {

    @GetMapping("/hi")
    public String hi() {
         InfoApi infoApi = HiController.getRestClient(InfoApi.class, "http://127.0.0.1:7010/");
         return infoApi.info(123456L);
    }

    public static <T> T getRestClient(Class<T> apiType, String url) {
        return Feign.builder().target(apiType, url);
    }

}
