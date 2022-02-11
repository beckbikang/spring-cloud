package cn.beckbi.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;

/**
 * @program: spring-cloud
 * @description: zk factory
 * @author: bikang
 * @create: 2022-02-10 22:25
 */
public class ZkFactory {

    public static CuratorFramework getCuratorFramework(String cluster, RetryPolicy retryPolicy, int connectTime, int sessionTime){
        return CuratorFrameworkFactory.builder()
                .connectString(cluster)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectTime)
                .sessionTimeoutMs(sessionTime)
                .build();

    }
}
