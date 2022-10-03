package cn.beckbi.controller;


import cn.beckbi.response.IdData;

import cn.beckbi.util.JacksonTool;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-02-09 23:04
 */
@Slf4j
@Api(tags={"用户接口"})
@RestController
public class UserController {



    @ApolloConfig
    private Config config;

    @Value("${user_info}")
    private String configData;

    @ApolloJsonValue("${user_info}")
    private IdData idData;


    private Optional<IdData> getIdDataFromConfig() {
        String data = config.getProperty("user_info", "");
        return Optional.ofNullable(JacksonTool.parseObject(data, IdData.class));
    }

    @GetMapping("/config1")
    public String configInfo1() {
        return configData;
    }

    @GetMapping("/config2")
    public IdData configInfo2() {
        return idData;
    }

    @GetMapping("/config3")
    public String configInfo3() {
        Config config = ConfigService.getConfig("application");
        return config.getProperty("user_info", "");
    }

    @GetMapping("/user/{uid}")
    public IdData info(@PathVariable long uid) {
        IdData idData = new IdData();
        idData.setUid(uid);
        idData.setId("");
        getIdDataFromConfig().ifPresent(
                data -> {
                    log.info("{}", data);
                    idData.setId(data.getId());
                }
        );
        log.info("apollo-config:"+ JacksonTool.toJSONString(config.getPropertyNames()));

        return idData;
    }

    @ApolloConfigChangeListener
    private void changer(ConfigChangeEvent changeEvent) {
        if(changeEvent.isChanged("user_info")) {
            log.info("user_info changeed");
        }
    }


}
