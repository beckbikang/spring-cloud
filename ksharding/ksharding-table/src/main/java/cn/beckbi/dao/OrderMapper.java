package cn.beckbi.dao;

import cn.beckbi.model.Order;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:42
 */
public interface OrderMapper {
    Order getUserById(long id);
    long insert(Order order);
}
