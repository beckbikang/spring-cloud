package cn.beckbi.route;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-22 07:47
 */
@Slf4j
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private static final String GATEWAY_ROUTE = "SPRING_CLOUD_GATEWAY_ROUTE_LIST";

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitionList = new ArrayList<>();
        redisTemplate.opsForHash().values(GATEWAY_ROUTE).stream().forEach(route -> {
            routeDefinitionList.add(JSON.parseObject(route.toString(), RouteDefinition.class));
        });
        return Flux.fromIterable(routeDefinitionList);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            redisTemplate.opsForHash().put(GATEWAY_ROUTE, routeDefinition.getId(), JSON.toJSONString(routeDefinition));
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id->{
            if(redisTemplate.opsForHash().hasKey(GATEWAY_ROUTE, id)){
                redisTemplate.opsForHash().delete(GATEWAY_ROUTE, id);
                return Mono.empty();
            }else {
                return Mono.defer(() -> Mono.error(new Exception("routeDefinition not found:" + routeId)));
            }
        });
    }
}
