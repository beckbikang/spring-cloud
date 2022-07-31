package cn.beckbi.service;

import cn.beckbi.model.User;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:43
 */
public interface UserService {
    /**
     * master获取数据
     * @param id Long
     * @return User
     */
    User getByIdFromMaster(Long id);

    /**
     * master获取数据
     * @param id Long
     * @return User
     */
    User getByIdFromMaster2(Long id);


    /**
     * slave获取数据
     * @param id Long
     * @return User
     */
    User getByIdFromSlave(Long id);
}
