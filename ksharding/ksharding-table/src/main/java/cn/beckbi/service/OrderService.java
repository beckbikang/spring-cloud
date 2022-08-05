package cn.beckbi.service;

import cn.beckbi.model.Order;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:43
 */
public interface OrderService {
    /**
     * master获取数据
     * @param uid Long
     * @return User
     */
    Order getByIdFromMaster(Long uid);



    /**
     * slave获取数据
     * @param uid Long
     * @return User
     */
    Order getByIdFromSlave(Long uid);


    Long insert(Order order);
}
