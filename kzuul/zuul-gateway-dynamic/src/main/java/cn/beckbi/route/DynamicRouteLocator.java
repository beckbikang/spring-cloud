package cn.beckbi.route;

import cn.beckbi.route.entity.ZuulEntity;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;


/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-14 13:04
 */
public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private static final String ZUUL_EKY = "LOCAL_ZUUL_RULES_REDIS_KEY";

    private ZuulProperties zuulProperties;

    public static String getRouteKey() {
        return ZUUL_EKY;
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public DynamicRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.zuulProperties = properties;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        Map<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap(20);

        zuulProperties.getRoutes().clear();

        routesMap.putAll(super.locateRoutes());

        routesMap.putAll(locateRoutesFromRedis());

        zuulProperties.getRoutes().putAll(routesMap);

        return routesMap;
    }

    private Map<? extends String, ? extends ZuulProperties.ZuulRoute> locateRoutesFromRedis() {
        LinkedHashMap routesMap = new LinkedHashMap();

        String routeData = stringRedisTemplate.opsForValue().get(ZUUL_EKY);
        List<ZuulEntity> zuulEntityList = JSONObject.parseObject(routeData, new TypeReference<List<ZuulEntity>>(){});

        for (ZuulEntity zuulEntity: zuulEntityList){
            routesMap.put(zuulEntity.getPath(),new ZuulProperties.ZuulRoute(
                    zuulEntity.getId(),
                    zuulEntity.getPath(),
                    zuulEntity.getServiceId(),
                    zuulEntity.getUrl(),
                    zuulEntity.isStripPrefix(),
                    zuulEntity.getRetryable(),
                    new LinkedHashSet(1)));
        }
        return routesMap;
    }

    @Override
    public Collection<String> getIgnoredPaths() {
        return super.getIgnoredPaths();
    }

    @Override
    public List<Route> getRoutes() {
        return super.getRoutes();
    }

    @Override
    public Route getMatchingRoute(String path) {
        return super.getMatchingRoute(path);
    }
}
