package cn.beckbi.route.entity;

import lombok.Data;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-14 13:24
 */
@Data
public class ZuulEntity {
    private String id;
    private String path;
    private String serviceId;
    private String url;
    private boolean stripPrefix = true;
    private Boolean retryable;
    private boolean customSensitiveHeaders = false;
}
