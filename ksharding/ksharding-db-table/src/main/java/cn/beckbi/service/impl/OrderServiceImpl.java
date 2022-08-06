package cn.beckbi.service.impl;


import cn.beckbi.dao.OrderMapper;
import cn.beckbi.model.Order;
import cn.beckbi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:50
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * master获取数据
     * @param id Long
     * @return User
     */
    @Override
    public List<Order> getListByUid(Long id) {
      return orderMapper.getOrdersByUid(id);
    }

}
