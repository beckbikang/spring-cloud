package cn.beckbi.commonpool.simple;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.Objects;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-24 23:26
 */

public class App {

    public static void main(String[] args) {

        TcpSessionFactory factory = new TcpSessionFactory();
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxTotal(120);
        poolConfig.setMinIdle(2);

        GenericObjectPool<TcpSession> objectPool = new GenericObjectPool<>(factory, poolConfig);


        for (int i = 0; i < 500; ++i ){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(factory);
                    TcpSession tcpSession = null;
                    try {
                        tcpSession = objectPool.borrowObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (!Objects.isNull(tcpSession)) {
                            objectPool.returnObject(tcpSession);
                        }
                    }

                }
            }).start();

        }



    }
}
