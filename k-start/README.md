通过spring启动的时候加载功能

定义属性

```java
@Data
@ConfigurationProperties("spring.data.auth.info")
public class AuthInfoProperties {

    private String user;
    private String token;
}
```

定义实体类

```java
public class AuthInfo {

    private AuthInfoProperties authInfoProperties;

    public AuthInfo(){}

    public AuthInfo(AuthInfoProperties authInfoProperties){
        this.authInfoProperties = authInfoProperties;
    }

    public String getUser(){
        return authInfoProperties.getUser();
    }

    public String getToken(){
        return authInfoProperties.getToken();
    }


}
```


定义自动加载策略

```java
@Configuration
@EnableConfigurationProperties(AuthInfoProperties.class)
public class AuthInfoAutoConfigure {

    @Bean
    @ConditionalOnProperty(prefix = "spring.data.auth.info", value = "enabled", havingValue = "true")
    public AuthInfo authInfo(AuthInfoProperties authInfoProperties){
        return new AuthInfo(authInfoProperties);
    }
}
```

定义注解

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AuthInfoAutoConfigure.class)
public @interface EnableAuthInfo {
}
```

在resources下定义META-INF支持跨包启动时spring的自动加载

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=cn.beckbi.starter.AuthInfoAutoConfigure
```

定义配置

```javascript
{
  "properties": [
    {
      "name": "spring.data.auth.info.enabled",
      "type": "java.lang.Boolean",
      "defaultValue": false
    }
  ]
}
```

