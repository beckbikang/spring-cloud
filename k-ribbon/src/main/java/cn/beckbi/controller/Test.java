package cn.beckbi.controller;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import rx.Observable;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-07 22:12
 */
@RestController
public class Test {

    @GetMapping("/test")
    public void hi() {

        // 服务列表
        List<Server> serverList = Arrays.asList(new Server("127.0.0.1", 8091), new Server("127.0.0.1", 8093));
        // 构建负载实例
        BaseLoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
        loadBalancer.setRule(new RoundRobinRule());

        for (int i = 0; i < 5; i++) {
            String result = LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer).build()
                    .submit(new ServerOperation<String>() {
                        @Override
                        public Observable<String> call(Server server) {
                            String host = "http://" + server.getHost() + ":" + server.getPort() + "\n";
                            return Observable.just(host);
                        }
                    }).toBlocking().first();
            System.out.println("host：" + result);
        }


    }
}
