# eureka

基于AP的服务注册中心

初步使用

```javascript
1. 依赖spring-boot
2. 引入serverce
3. 注册服务
```

如何做到高可用
* 使用多个注册中心，注册中心注册除了它自己外的所有机器，包括其他注册中心
* 服务的监控




核心类
* InstanceInfo 代表注册的服务实例
* LeaseInfo 标识应用实例租约信息
* ServiceInstance 是spring-cloud对service discovery的实例的抽象接口
* InstanceStatus 用于标识服务实例的状态

服务核心操作
* 服务注册
* 服务下线
* 服务续约
* 服务剔除


设计理念及其解决的问题
* 服务实例如何注册到服务中心
* 服务实例如何从服务中心剔除
* 服务实例信息的一致性问题


CAP里面的AP优于CP

Peer to Peer架构，对等复制
* 客户端维护一个可用，不可用的server列表
* 服务端依赖client，每个server又是其他server的client，使用header_replication区分请求操作
与普通应用实例的正常请求，通过心跳检查复制不一致问题

eureka支持zone和region
































