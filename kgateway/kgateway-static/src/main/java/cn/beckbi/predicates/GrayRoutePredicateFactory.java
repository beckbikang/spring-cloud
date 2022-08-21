package cn.beckbi.predicates;

import cn.beckbi.predicates.routcfg.GrayCfg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-21 16:35
 */
@Slf4j
@Component
public class GrayRoutePredicateFactory extends AbstractRoutePredicateFactory<GrayCfg> {

    public GrayRoutePredicateFactory() {
        super(GrayCfg.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(GrayCfg cfg) {

        return serverWebExchange -> {
            log.info("enter GrayRoutePredicateFactory"+cfg.isGrayStatus());
            if (cfg.isGrayStatus()) {
                log.info(" GrayRoutePredicateFactory hit   start gray");
                return true;
            }

            return false;
        };
    }
}
