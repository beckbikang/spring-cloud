package cn.beckbi.keureka.server.listener;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @program: spring-cloud
 * @description: KeurekaServer listener
 * @author: bikang
 * @create: 2022-02-07 22:34
 */
@Slf4j
@Component
public class KeurekaServerListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        log.info("cancel:"+event.getServerId() + "-" + event.getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info("registered:"+instanceInfo.getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        log.info("Renewed:"+event.getServerId() + "-" + event.getAppName());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.info("Eureka Server  RegistryAvailable");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.info("Eureka Server Started");
    }
}
