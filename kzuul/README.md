# 使用文档


## 环境配置

仅在测试环境打开哦，生成环境需要关闭
management.endpoints.web.exposure.include=* 
management.endpoint.health.show-details=ALWAYS




## eureka服务

使用eureka网关
- http://127.0.0.1:9010/

eureka所有服务的信息
- http://localhost:9010/eureka/apps

## 用户服务

直接访问
- http://127.0.0.1:9015/user/1234



## zuul网关转发 

查看所有routers的配置
- http://localhost:9011/actuator/routes
- http://localhost:9011/actuator/filters


1. 访问服务：
- http://localhost:9011/abc/123

2. 基础的匹配

```
zuul.routes.test1.path=/abc/**
zuul.routes.test1.url=http://www.example.com/
```

3. 本地跳转
```
zuul.routes.localpath.path=/a/**
zuul.routes.localpath.url=forward:/a
```




4. 使用eureka的匹配
- http://localhost:9011/user/1234

```
zuul.routes.userinfo.path=/user/**
zuul.routes.userinfo.service-id=zuul-user
zuul.routes.userinfo.stripPrefix=false
zuul.routes.userinfo.sensitive-headers=true
zuul.routes.userinfo.customSensitiveHeaders=true
```

5. 自定义过滤器
- pre 请求被路由之前调用
- route 请求路由时调用
- post 在route和error过滤器之后调用
- error 在请求发生错误时调用






