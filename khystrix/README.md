# 服务


## 访问地址

http://127.0.0.1:7330/user/123

@HystrixCommand(fallbackMethod = "defaultCall")


## 压测

ab -n 100 -c 20 'http://127.0.0.1:7330/user/123'

## 监控

http://127.0.0.1:7330/actuator