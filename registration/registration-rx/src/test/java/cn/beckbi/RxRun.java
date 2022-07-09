package cn.beckbi;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-cloud
 * @description: rx run
 * @author: bikang
 * @create: 2022-02-24 22:15
 */
@Slf4j
public class RxRun {


    @Test
    public void example(){

        //create
        Observable observable = Observable.just(
                "apple", "bannana", "pear"
        );
        observable.subscribe(s->log.info(s.toString()));

        observable.subscribe(
                s->log.info(s.toString()),
                s->log.info("data:"+s.toString())
        );
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        Observable observable1 = Observable.fromIterable(list);
        observable1.subscribe(s -> log.info(s.toString()));
        Observable.range(1,5).subscribe(s -> {
            log.info("show:{}",s);
        });
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> {
                    log.info("data:{}"+aLong);
                });

        Observable observable2 = Observable.defer(
                ()->Observable.just("abc")
        );

        observable2.subscribe(
          str -> {
              log.info("str:"+str);
          }
        );

        //filter
        Observable.range(1,10).distinct().filter(integer -> {
            return integer%5 == 0;
        }).subscribe(integer -> {
            log.info("re:"+integer);
        });

        //convert
        Observable.range(1,3).map(i -> i*i)
                .subscribe(integer -> {
                    log.info("re:"+integer);
                });
        Observable.range(1,3).flatMap(
                i -> Observable.just(i*i, i*i +1))
                .subscribe(integer -> {
                    log.info("re2:"+integer);
                }
        );

        //count
        Long count = Observable.fromIterable(list).count().blockingGet();
        log.info("count={}", count);

        Observable.fromIterable(list).reduce((i,j)->{
            return i+j;
        }).subscribe(integer -> {
            log.info("reduce:"+integer);
        });

        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .map(aLong -> 10-aLong)
                .subscribe(integer -> {
                    log.info("take:"+integer);
                });

        Observable.fromIterable(list).window(3).flatMap(i -> Observable.just(i.toList())).subscribe(l1 -> {
            log.info("reduce:"+l1.toString());
        });


    }
}
