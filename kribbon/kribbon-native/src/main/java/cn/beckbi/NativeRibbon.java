package cn.beckbi;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


import com.google.common.collect.Lists;
import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.loadbalancer.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import rx.Observable;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * 源自官方
 *
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-24 10:35
 */
public class NativeRibbon {

    private final ILoadBalancer loadBalancer;

    private final RetryHandler retryHandler = new DefaultLoadBalancerRetryHandler(0, 1, true);


    public NativeRibbon(List<Server> serverList) {
        loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
    }


    /**
     * 获取服务端ip
     *
     * @return String
     */
    public String getServerIp(String path) throws Exception {
        return LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer).withRetryHandler(retryHandler).build().submit(
                new ServerOperation<String>() {
                    @Override
                    public rx.Observable<String> call(Server server) {
                        URL url;
                        try {
                            url = new URL("http://" + server.getHost() + ":" + server.getPort() + path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            return Observable.just(conn.getResponseMessage());
                        } catch (Exception e) {
                            return Observable.error(e);
                        }
                    }
                }
        ).toBlocking().first();
    }

    public LoadBalancerStats getLoadBalancerStats() {
        return ((BaseLoadBalancer) loadBalancer).getLoadBalancerStats();
    }

    public static void main(String[] args) throws Exception {

        List<Server> serverList = Arrays.asList(new Server("baidu.com", 80), new Server("sina.com.cn", 80));
        NativeRibbon nativeRibbon = new NativeRibbon(serverList);

        for (int times = 0; times < 10; times++) {
            System.out.println(nativeRibbon.getServerIp(""));
        }


    }
}
