# consul


http://127.0.0.1:8500/ui/dc1/services


consume1
- http://127.0.0.1:7302/ok


produce1
- http://127.0.0.1:7301/info/12312

## 断言

网关
- http://127.0.0.1:10011/info/12312
- http://127.0.0.1:10011/abc/12312


path断言
- http://127.0.0.1:10011/abcd/12312

query路由断言
- http://127.0.0.1:10011/abcdeeee?foo=bb

方法断言
```
curl --location --request DELETE 'http://127.0.0.1:10011/adfasfsdfdsd'
```

head断言
```
curl --location --request GET 'http://127.0.0.1:10011/adfasfsdfdsd12312' \
--header 'x-ragnar-traceid: 123213123'
```


## 过滤器

```
- RemoveRequestHeader=from2
- AddRequestHeader=from,abc
```


http://127.0.0.1:10011/info/12312?foo=b1

状态设置: http://127.0.0.1:10011/adfsdfsd/12312?foo=b1

```$xslt
- id: statusFilter
  uri: http://www.example.com/
  predicates:
    - Query=foo,b1
  filters:
    - SetStatus=401
```

重定向

```$xslt
- id: redictFilter
  uri: http://www.example.com/
  predicates:
    - Query=foo,b2
  filters:
    - RedirectTo=302,http://weibo.com
```

自定义登录

```
- id: lb-info
  uri: lb://consul-server-producer
  predicates:
    - Path=/info/**
  filters:
    - RemoveRequestHeader=from2
    - AddRequestHeader=from,abc
    - name: Login
      args:
        checkLogin: true
```






