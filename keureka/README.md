# eureka实践

## eurek 集群

构建一组eureka服务 server1和server2，组成最小的注册中心集群
暂时关闭自我保护

server host

http://127.0.0.1:6010/





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
