package cn.beckbi.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: spring-cloud
 * @description: shardingjdbcaop
 * @author: bikang
 * @create: 2022-07-31 22:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ShardingJdbcForceMaster {
}
