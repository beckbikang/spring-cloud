package server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: spring-cloud
 * @description: Security Config
 * @author: bikang
 * @create: 2022-02-07 22:32
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * add security
     * @param httpSecurity HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
