package cn.beckbi.dao;

import cn.beckbi.model.User;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:42
 */
public interface UserMapper {
    User getUserById2(Long id);
}
