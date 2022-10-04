package cn.beckbi.cron;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-04 16:30
 */
@Data
@Slf4j
@Component
public class XxlCron {

    private static final String FORMAT_STR_TIME = "yyyy-MM-dd HH:mm:ss";

    private String currentTime = "";


    public static String getCurrentDateSimpleTpl() {
        return getCurrentDateSimpleTpl(FORMAT_STR_TIME, System.currentTimeMillis()/1000);
    }

    private static String getCurrentDateSimpleTpl(String tpl, long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(tpl);
        return localDateTime.format(formatter2);
    }




    @XxlJob("testRunXxl")
    public ReturnT<String> execute() {
        XxlJobHelper.log("XXL-JOB testRunXxl start");

        String currentTime = getCurrentDateSimpleTpl();

        log.info("currentTime:"+currentTime);
        this.currentTime = currentTime;
        return ReturnT.SUCCESS;
    }
}