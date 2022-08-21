package cn.beckbi.filter;

import cn.beckbi.filter.filtercfg.LoginCfg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-21 20:07
 */
@Slf4j
@Component
public class LoginGatewayFilterFactory extends AbstractGatewayFilterFactory<LoginCfg> {

    public LoginGatewayFilterFactory() {
        super(LoginCfg.class);
    }

    @Override
    public GatewayFilter apply(LoginCfg loginCfg) {
        return (exchange, chain) -> {
            log.info("if us check login:"+ loginCfg.isCheckLogin());
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .build();


            if (loginCfg.isCheckLogin()) {
                log.info("do login checking.......");
            }

            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}
