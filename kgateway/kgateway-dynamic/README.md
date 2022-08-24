# 动态路由

实现接口 RouteDefinitionRepository


刷新

http://localhost:10013/actuator/gateway/refresh

http://localhost:10013/actuator/gateway/routes

增加配置
```
curl --location --request POST 'http://localhost:10013/actuator/gateway/routes/test1' \
--header 'Content-Type: application/json' \
--data-raw '{"uri":"http://www.example.com/","predicates":[{"name":"Path","args":{"pattern":"/abc/**"}}],"filters":[{"name":"StripPrefix","args":{"value":1}}],"order":0}'
```