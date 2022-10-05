package cn.beckbi.cron;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-05 16:51
 */
@Slf4j
@Component
public class SpringBootSimpleJob implements SimpleJob {



    @Override
    public void execute(final ShardingContext shardingContext) {
        log.info("Item: {} | Time: {} | Thread: {} | {}",
                shardingContext.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread.currentThread().getId(), "SIMPLE");
    }
}
