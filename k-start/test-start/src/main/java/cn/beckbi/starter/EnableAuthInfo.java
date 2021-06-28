package cn.beckbi.starter;


import java.lang.annotation.*;
import org.springframework.context.annotation.Import;

/**
 * @program: spring-cloud
 * @description: auth info
 * @author: bikang
 * @create: 2021-06-27 19:37
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AuthInfoAutoConfigure.class)
public @interface EnableAuthInfo {
}
