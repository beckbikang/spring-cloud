package cn.beckbi.dao;

import cn.beckbi.model.Order;

import java.util.List;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:42
 */
public interface OrderMapper {
    List<Order> getOrdersByUid(Long uid);
}
