package cn.beckbi.service;

import cn.beckbi.model.Order;

import java.util.List;

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
    List<Order> getListByUid(Long uid);



}
