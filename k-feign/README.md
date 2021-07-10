feign提供的功能
* 使用方式接口加上注解
* 可插拔的注解方式
* 支持http编码和解码器
* 支持hystrix和fallback
* 支持ribbon的负载君合
* 支持http请求和响应的压缩



工作原理
* 开启注解，扫描包，根据定义的接口加上feginclient注解的接口
* 当注解被调用时，使用jdk代码生成具体的request-templete对象
* 由request-templete对象生成对应的request，把request交给client去处理
* 支持url-connection，httpclient，okhttp
* 最后client被封装到loadbalanceclient类里面


feign支持eureka和consul之类的服务发现