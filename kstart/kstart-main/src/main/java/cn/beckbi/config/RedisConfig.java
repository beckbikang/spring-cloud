package cn.beckbi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-02 21:25
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig  extends CachingConfigurerSupport{

    @Value("${spring.redis1.database}")
    private Integer database;
    @Value("${spring.redis1.host}")
    private String host;
    @Value("${spring.redis1.port}")
    private Integer port;
    @Value("${spring.redis1.password}")
    private String password;
    @Value("${spring.redis1.lettuce.pool.max-active}")
    private Integer maxActive;
    @Value("${spring.redis1.lettuce.pool.max-wait}")
    private Integer maxWait;
    @Value("${spring.redis1.lettuce.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis1.lettuce.pool.min-idle}")
    private Integer minIdle;
    @Value("${spring.redis1.lettuce.shutdown-timeout}")
    private Integer timeout;

    @Value("${spring.redis2.database}")
    private Integer database2;
    @Value("${spring.redis2.host}")
    private String host2;
    @Value("${spring.redis2.port}")
    private Integer port2;
    @Value("${spring.redis2.password}")
    private String password2;
    @Value("${spring.redis2.lettuce.pool.max-active}")
    private Integer maxActive2;
    @Value("${spring.redis2.lettuce.pool.max-wait}")
    private Integer maxWait2;
    @Value("${spring.redis2.lettuce.pool.max-idle}")
    private Integer maxIdle2;
    @Value("${spring.redis2.lettuce.pool.min-idle}")
    private Integer minIdle2;
    @Value("${spring.redis2.lettuce.shutdown-timeout}")
    private Integer timeout2;

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Bean(name="slaveRedisTemplate")
    public RedisTemplate<Object, Object> slaveRedisTemplate() {
        return getTemplate(connectionFactory(maxActive, maxIdle, minIdle,
                maxWait, host, password, timeout, port, database));
    }

    @Bean(name="masterRedisTemplate")
    public RedisTemplate<Object, Object> masterRedisTemplate() {
        return getTemplate(connectionFactory(maxActive2, maxIdle2,
                minIdle2, maxWait2, host2, password2, timeout2, port2, database2));
    }



    private RedisConnectionFactory connectionFactory(Integer maxActive,
                                                     Integer maxIdle,
                                                     Integer minIdle,
                                                     Integer maxWait,
                                                     String host,
                                                     String password,
                                                     Integer timeout,
                                                     Integer port,
                                                     Integer database) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        LettuceClientConfiguration lettucePoolingConfig = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig).shutdownTimeout(Duration.ofMillis(timeout)).build();
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration,
                lettucePoolingConfig);
        connectionFactory.afterPropertiesSet();

        return connectionFactory;
    }


    private RedisTemplate<Object, Object> getTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setValueSerializer(jackson2JsonRedisSerializer(new ObjectMapper()));
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer(new ObjectMapper()));
        template.afterPropertiesSet();
        return template;
    }


    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(ObjectMapper objectMapper) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return jackson2JsonRedisSerializer;
    }

}
