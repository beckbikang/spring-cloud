package cn.beckbi.model;

import lombok.Data;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:42
 */
@Data
public class Order {

    private Long id;
    private Long uid;
    private String name;
}
