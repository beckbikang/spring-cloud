package cn.beckbi.base;

import cn.beckbi.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * @program: spring-cloud
 * @description: zk base operation
 * @author: bikang
 * @create: 2022-02-10 22:29
 */
@Slf4j
public class ZkBase extends TestApplication {

    @Autowired
    CuratorFramework curatorFramework;



    @Test
    public void testZkBase() throws Exception{
        curatorFramework.start();


        String path = "/zk/test/node-1";
        byte[] data = "hello".getBytes("UTF-8");

        long stime = System.currentTimeMillis();
        Stat stat = curatorFramework.checkExists().forPath(path);
        log.info("forPath耗费时间{}ms", (System.currentTimeMillis()-stime));

        if (Objects.isNull(stat)) {
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path, data);
            log.info("创建持久耗费时间{}ms", (System.currentTimeMillis()-stime));
        }

        curatorFramework.setData().forPath(path,"hello22".getBytes("UTF-8"));
        log.info("更新持久耗费时间{}ms", (System.currentTimeMillis()-stime));

        //异步
        AsyncCallback.StringCallback callback = new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int i, String s, Object o, String s1) {
                log.info(
                        "i = " + i + " | " +
                                "s = " + s + " | " +
                                "o = " + o + " | " +
                                "s1 = " + s1
                );
            }
        };
        curatorFramework.setData().inBackground(callback
        ).forPath(path,"hello333".getBytes("UTF-8"));

        Thread.sleep(1000);
        stime = System.currentTimeMillis();

        byte[] payload = curatorFramework.getData().forPath(path);
        log.info("payload:{}",payload.toString());
        log.info("读取持久耗费时间{}ms", (System.currentTimeMillis()-stime));



        curatorFramework.delete().forPath(path);
        log.info("删除持久耗费时间{}ms", (System.currentTimeMillis()-stime));

        String pathTemp = "/zk/test/node-tmp-1";
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(pathTemp, data);
        log.info("创建临时耗费时间{}ms", (System.currentTimeMillis()-stime));

        String pathOrder = "/zk/test/node-order-";
        for (int i = 0; i < 10; i++) {
            String nodeStr = curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(pathOrder, data);
            log.info("创建持久节点耗费时间{}ms", (System.currentTimeMillis()-stime));
            int index = nodeStr.lastIndexOf(pathOrder);
            if (index >= 0) {
                index += pathOrder.length();
                String id = index <= nodeStr.length() ? nodeStr.substring(index) : "";

                log.info("id:{}",id);
            }
        }


        String parentPath = "/zk/test";
        List<String> children = curatorFramework.getChildren().forPath(parentPath);

        for (String child : children) {
            log.info("child-{}", child);
        }




        curatorFramework.close();
    }
}
