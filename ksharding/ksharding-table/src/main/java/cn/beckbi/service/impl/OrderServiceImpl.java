package cn.beckbi.service.impl;

import cn.beckbi.aop.ShardingJdbcForceMaster;
import cn.beckbi.dao.OrderMapper;
import cn.beckbi.model.Order;
import cn.beckbi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    @ShardingJdbcForceMaster
    @Override
    public Order getByIdFromMaster(Long id) {
      return orderMapper.getUserById(id);
    }
    /**
     * slave获取数据
     * @param id Long
     * @return User
     */
    @Override
    public Order getByIdFromSlave(Long id) {
        return orderMapper.getUserById(id);
    }


    @Override
    public Long insert(Order order) {
        return orderMapper.insert(order);
    }
}
