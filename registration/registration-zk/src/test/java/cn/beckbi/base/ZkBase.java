package cn.beckbi.base;

import cn.beckbi.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
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


    }

    @Test
    public void testLock() throws Exception{

        curatorFramework.start();


        String parentPath = "/zk/test";
        List<String> children = curatorFramework.getChildren().forPath(parentPath);

        for (String child : children) {
            log.info("child-{}", child);
        }


        //test lock
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/zk/test/mutex");

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int count = 0;
                        mutex.acquire();
                        for (int j = 0; j < 10; j++) {
                            count++;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("count = " + count);
                        mutex.release();

                    } catch (Exception e) {
                        log.info("error", e);
                    }

                }
            }).start();

        }
        Thread.sleep(2000);
    }


    @Test
    public void testWatch() throws Exception{
        curatorFramework.start();
        String workerPath = "/zk/watch/data2";

        Stat stat = curatorFramework.checkExists().forPath(workerPath);
        if (Objects.isNull(stat)) {
            curatorFramework.create()
                       .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(workerPath, null);
        }

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info("data change:{}", watchedEvent);
            }
        };
        curatorFramework.getData().usingWatcher(watcher).forPath(workerPath);

        curatorFramework.setData().forPath(workerPath, "first-data".getBytes());

        curatorFramework.setData().forPath(workerPath, "second-data".getBytes());


    }

    @Test
    public void testNodeWatch() throws Exception{

        curatorFramework.start();
        String workerPath = "/zk/watch/data2";

        Stat stat = curatorFramework.checkExists().forPath(workerPath);
        if (Objects.isNull(stat)) {
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(workerPath, null);
        }

        NodeCache nodeCache = new NodeCache(curatorFramework, workerPath, false);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData childData = nodeCache.getCurrentData();
                log.info("change:path:{}", childData.getPath());
                log.info("change:data:{}", childData.getData().toString());
                log.info("change:stat:{}", childData.getStat());
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();

        curatorFramework.setData().forPath(workerPath, "first-data".getBytes());

        Thread.sleep(1000);

        curatorFramework.setData().forPath(workerPath, "second-data".getBytes());

        Thread.sleep(1000);
    }

    @Test
    public void testNodeWatchChild() throws Exception{

        curatorFramework.start();
        String workerPath = "/zk/watch/data2";

        Stat stat = curatorFramework.checkExists().forPath(workerPath);
        if (Objects.isNull(stat)) {
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(workerPath, null);
        }

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, workerPath, true);
        PathChildrenCacheListener pathChildrenCacheListener =
                new PathChildrenCacheListener() {
                    @Override
                    public void childEvent(CuratorFramework client,
                                           PathChildrenCacheEvent event) {
                        try {
                            ChildData data = event.getData();
                            switch (event.getType()) {
                                case CHILD_ADDED:
                                    log.info("子节点增加, path={}, data={}",
                                            data.getPath(), new String(data.getData(), "UTF-8"));

                                    break;
                                case CHILD_UPDATED:
                                    log.info("子节点更新, path={}, data={}",
                                            data.getPath(), new String(data.getData(), "UTF-8"));
                                    break;
                                case CHILD_REMOVED:
                                    log.info("子节点删除, path={}, data={}",
                                            data.getPath(), new String(data.getData(), "UTF-8"));
                                    break;
                                default:
                                    break;
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        Thread.sleep(1000);

        for (int i=0; i < 3; ++i) {
            String workerPathChild = workerPath+"/node-"+i;

            String nodeStr = curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(workerPathChild, ("abc"+i).getBytes());

            Stat stat2 = curatorFramework.checkExists().forPath(workerPath);

        }
        Thread.sleep(1000);
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(workerPath);
    }


    @Test
    public void testTreeNodeWatch() throws Exception{

        curatorFramework.start();
        String workerPath = "/zk/watch/data2";

        Stat stat = curatorFramework.checkExists().forPath(workerPath);
        if (Objects.isNull(stat)) {
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(workerPath, "test".getBytes());
        }


            TreeCache treeCache =
                    new TreeCache(curatorFramework, workerPath);
            TreeCacheListener l =
                    new TreeCacheListener() {
                        @Override
                        public void childEvent(CuratorFramework client,
                                               TreeCacheEvent event) {
                            try {
                                ChildData data = event.getData();
                                if (data == null) {
                                    log.info("data is null");
                                    return;
                                }
                                switch (event.getType()) {
                                    case NODE_ADDED:

                                        log.info("[TreeCache]节点增加, path={}, data={}",
                                                data.getPath(), new String(data.getData(), "UTF-8"));

                                        break;
                                    case NODE_UPDATED:
                                        log.info("[TreeCache]节点更新, path={}, data={}",
                                                data.getPath(), new String(data.getData(), "UTF-8"));
                                        break;
                                    case NODE_REMOVED:
                                        log.info("[TreeCache]节点删除, path={}, data={}",
                                                data.getPath(), new String(data.getData(), "UTF-8"));
                                        break;
                                    default:
                                        break;
                                }

                            } catch (
                                    UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    };
            treeCache.getListenable().addListener(l);
            treeCache.start();


        curatorFramework.setData().forPath(workerPath, "first-data".getBytes());

        Thread.sleep(1000);

        curatorFramework.setData().forPath(workerPath, "second-data".getBytes());

        Thread.sleep(1000);

        Thread.sleep(1000);

        for (int i=0; i < 3; ++i) {
            String workerPathChild = workerPath+"/node-"+i;

            String nodeStr = curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(workerPathChild, ("abc"+i).getBytes());

            Stat stat2 = curatorFramework.checkExists().forPath(workerPath);

        }
        Thread.sleep(1000);
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(workerPath);
    }



}

