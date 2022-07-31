package cn.beckbi.model;

import lombok.Data;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:42
 */
@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
}
