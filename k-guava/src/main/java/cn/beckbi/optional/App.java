package cn.beckbi.optional;


import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-06 22:56
 */
public class App
{

    @ToString
    @Data
    static class A{
        int a;
        int b;
        A(int a){
            this.a = a;
        }
        A(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args){
        A aObj = new A(11);

        //设置和获取
        Optional<A> optional = Optional.of(aObj);
        System.out.println(optional.get());

        //空
        List<A> aList = new ArrayList<>(3);
        aList.add(null);
        Optional<A> optional1 = Optional.ofNullable(aList.get(0));

        //如果存在
        optional1.ifPresent( a-> System.out.println(a));


        //默认值
        System.out.println(Optional.ofNullable(aList.get(0)).orElse(new A(1231321)));
        System.out.println(Optional.ofNullable(aList.get(0)).orElseGet(()->new A(3333)));

        //抛出异常
        try{
            Optional.ofNullable(aList.get(0)).orElseThrow(() -> new IllegalArgumentException("空数据"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //转化
        System.out.println(Optional.ofNullable(new A(22))
                .map(u -> u.getA()).orElse(31));

        System.out.println(Optional.ofNullable(new A(22))
                .filter(u -> u.getA() == 11));

        //链
        int result = Optional.ofNullable(new A(111, 222))
                .map(a -> a.getA())
                .orElse(23333);
        System.out.println("result:"+result);



    }

}
