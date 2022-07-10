package cn.beckbi;

import cn.beckbi.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: spring-cloud
 * @description: hello server
 * @author: bikang
 * @create: 2022-07-10 10:32
 */
@Slf4j
public class HelloServer extends AbstractVerticle  {
    public static void main(String[] args) {
        Runner.runExample(HelloServer.class);
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
        });

        vertx.createHttpServer().requestHandler(router).listen(8181);
    }
}
