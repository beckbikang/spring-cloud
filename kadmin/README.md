# admin-server

## 访问admin-server

配置server

```
<dependencies>
    <!-- https://mvnrepository.com/artifact/de.codecentric/spring-boot-admin-starter-server -->
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
        <version>2.5.2</version>
    </dependency>
</dependencies>
```

http://127.0.0.1:10800/applications

http://127.0.0.1:10800/wallboard

配置client

```
    <dependencies>
        <!-- https://mvnrepository.com/artifact/de.codecentric/spring-boot-admin-starter-client -->
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-client</artifactId>
            <version>2.5.2</version>
        </dependency>
    </dependencies>
```