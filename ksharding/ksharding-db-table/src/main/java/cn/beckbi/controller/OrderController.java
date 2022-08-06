package cn.beckbi.controller;


import cn.beckbi.common.ResultBuilder;
import cn.beckbi.entity.result.Response;
import cn.beckbi.model.Order;
import cn.beckbi.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:44
 */
@Slf4j
@RequestMapping(value="api/v1/order",produces = "application/json;charset=utf-8")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/uid/{id}")
    public Response<List<Order>> getMasterOrder(@PathVariable("id") Long uid)   throws JsonProcessingException {
        return ResultBuilder.builderResp(orderService.getListByUid(uid));
    }





}
