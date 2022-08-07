package cn.beckbi;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-07 11:09
 */
public class Example1 extends HystrixCommand<String>
{

    private String name;

    public Example1(String  name) {
        super(
                HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationStrategy(
                                                HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
                                        )
                        ).andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withMaxQueueSize(100)
                        .withMaximumSize(100)
                )
        );
        this.name = name;
    }

    @Override
    protected String run() {
        /*
        try{
            TimeUnit.MICROSECONDS.sleep(1);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("get data");
        return "HystrixCommandName:"+this.name+"## currentThreadName="+Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return Thread.currentThread().getName()+"失败了";
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }

    public static void main(String[] args) throws Exception{

        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();

        Example1 example1 = new Example1("test1");
        String result = example1.execute();
        System.out.println("## currentThreadName="+Thread.currentThread().getName());
        System.out.println(result);

        Future<String> future = new Example1("test2").queue();
        System.out.println("future:"+future.get());



        result = new Example1("test3").execute();

        future = new Example1("test3").queue();
        System.out.println("future:"+future.get());
        hystrixRequestContext.shutdown();



    }
}
