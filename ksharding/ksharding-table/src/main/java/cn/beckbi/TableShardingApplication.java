package cn.beckbi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 10:17
 */
@MapperScan("cn.beckbi.dao")
@SpringBootApplication
public class TableShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableShardingApplication.class, args);
    }
}
