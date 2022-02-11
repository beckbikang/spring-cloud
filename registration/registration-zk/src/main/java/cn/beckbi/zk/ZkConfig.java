package cn.beckbi.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-cloud
 * @description: zk config
 * @author: bikang
 * @create: 2022-02-10 22:24
 */
@Configuration
public class ZkConfig {

    @Bean
    public CuratorFramework getDefaultCuratorFramework() {
        return ZkFactory.getCuratorFramework("tt.com:12181",
                new ExponentialBackoffRetry(1000, 4),
                1000,
                1000
                );
    }

}
