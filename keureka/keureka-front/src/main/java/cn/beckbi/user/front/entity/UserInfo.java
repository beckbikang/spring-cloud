package cn.beckbi.user.front.entity;

import lombok.Data;

/**
 * @program: spring-cloud
 * @description: user info
 * @author: bikang
 * @create: 2022-02-09 23:07
 */
@Data
public class UserInfo {
    private Long id;
    private String name;
    private Integer age;
    private Float height;
}
