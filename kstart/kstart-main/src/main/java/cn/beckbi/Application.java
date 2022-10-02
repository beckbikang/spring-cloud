package cn.beckbi;

import cn.beckbi.build.id.EnableIdMaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: spring-cloud
 * @description: application
 * @author: bikang
 * @create: 2022-07-09 21:59
 */

@EnableIdMaker
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
