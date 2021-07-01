package cn.beckbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-01 23:06
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp2 {


    public static void main(String[] args){
        SpringApplication.run(EurekaServerApp2.class, args);
    }

    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //关闭csrf
            http.csrf().disable();
            // 支持httpBasic
            http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        }
    }
}
