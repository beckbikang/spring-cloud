# eureka实践

## eurek 集群

构建一组eureka服务 server1和server2，组成最小的注册中心集群
暂时关闭自我保护

server host

http://127.0.0.1:6010/


## zipkin

运行zipkin

```
java curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```

sleuth

- 生成各类traceid

zipkin支持

- 写入消息队列然后再收集
- 支持定时任务
- 支持内存存储，数据库和es存储


代码改动

配置

```
spring.zipkin.base-url=http://localhost:9411
spring.sleuth.sampler.probability=1.0
```

在前面的基础上加上

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
            <version>2.2.8.RELEASE</version>
        </dependency>
```

![image.png](https://ask.qcloudimg.com/http-save/yehe-3594240/afc466f3d30ffca2dd727946510f18a9.png)



### 2.1 使用eureka做一个简单的微服务

构建一个简单的微服务
* 前台服务front(front service)
* 用户服务(user service)
* 内容服务(content service)
* 用户金额(account money service)
* 分布式发号器服务(id-maker service)

采用的技术
* eureka 分布式注册中心
* feign 服务调用
* ribbon 负载均衡
* hystrix 限流熔断
* swagger 作为文档中心
* guava 做本地cache
* redis 做分布式cache
* mysql作为存储
* mybatis作为orm
* sharding-jdbc作为分表插件
* apollo作为配置中心
* 基于zuul的网关
* 基于spring-cloud-gateway的网关
* sleuth作为链路追踪
* spring-admin作为微服务的监控
* spring-security作为服务之间的安全校验


