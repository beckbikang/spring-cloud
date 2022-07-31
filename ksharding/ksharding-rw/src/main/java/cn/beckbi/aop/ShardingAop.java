package cn.beckbi.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 22:16
 */
@Slf4j
@Component
@Aspect
public class ShardingAop {

    @Around("execution(* cn.beckbi.service.impl.*.*(..))")
    public Object master(ProceedingJoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Object ret = null;
        log.info(joinPoint.toShortString());

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ShardingJdbcMaster shardingJdbcMaster = method.getAnnotation(ShardingJdbcMaster.class);

        HintManager hintManager = null;
        try {
            if (Objects.nonNull(shardingJdbcMaster)) {
                HintManager.clear();
                hintManager = HintManager.getInstance();
                hintManager.setMasterRouteOnly();
            }
            ret = joinPoint.proceed(args);
        }catch (Exception ex){
            log.error("exception error",ex);
        }catch (Throwable ex2){
            log.error("Throwable",ex2);
        }finally {
            if (Objects.nonNull(shardingJdbcMaster) && Objects.nonNull(hintManager)) {
                hintManager.close();
            }
        }
        return ret;
    }
}
