# java common software practice

## 1 common library

- rxjava
- zookeeper
- sharding-jdbc
    - Read write separation database
    - force master database
    - separation database and separation table


## 2 spring-cloud Microservices

- spring-boot-starter
- eureka-server and eureka-client
- ribbon
- feign 
- zipkin
- sleuth

## 3 Domain Driven Architecture


## 4 vertx
- vertx-hellowrod 

## 5 search
- use elasticsearch todo
- lucene  todo



## example


use spring aop force database mysql in sharding-jdbc

```java
@ShardingJdbcMaster
@Override
public User getByIdFromMaster2(Long id) {
    return userMapper.getUserById(id);
}
```







