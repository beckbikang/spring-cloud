package cn.beckbi.config;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-10 18:32
 */

import feign.Feign;
import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignOkhttpClientConfig {
    @Bean
    public okhttp3.OkHttpClient okHttpClient(){
        return new okhttp3.OkHttpClient.Builder()
                //设置连接超时
                .connectTimeout(2, TimeUnit.SECONDS)
                //设置读超时
                .readTimeout(3, TimeUnit.SECONDS)
                //设置写超时
                .writeTimeout(3,TimeUnit.SECONDS)
                //是否自动重连
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool())
                //构建OkHttpClient对象
                .build();
    }

}
