package cn.beckbi;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-07 11:10
 */
public class Example2 extends HystrixCommand<String> {

    private String name;

    public Example2(String  name) {
        super(
                HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(
                                HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
                        )
                )
        );
        this.name = name;
    }

    @Override
    protected String run() {
        return "HystrixCommandName:"+this.name+"## currentThreadName="+Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "失败了";
    }

    public static void main(String[] args) {
        Example2 example2 = new Example2("test1");
        String result = example2.execute();
        System.out.println("## currentThreadName="+Thread.currentThread().getName());
        System.out.println(result);

        /*
        Future<String> future = example2.queue();
        System.out.println("future:"+future.get());

         */

    }
}
