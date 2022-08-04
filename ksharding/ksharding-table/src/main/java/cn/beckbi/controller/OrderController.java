package cn.beckbi.controller;


import cn.beckbi.common.ResultBuilder;
import cn.beckbi.entity.result.Response;
import cn.beckbi.model.Order;
import cn.beckbi.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private OrderService userService;

    @GetMapping(value = "/uid/{id}")
    public Response<Order> getMasterOrder(@PathVariable("id") Long uid)   throws JsonProcessingException {
        return ResultBuilder.builderResp(userService.getByIdFromMaster(uid));
    }


    @GetMapping(value = "/uid/{id}")
    public Response<Order> getSlaveOrder(@PathVariable("id") Long uid)   throws JsonProcessingException {
        return ResultBuilder.builderResp(userService.getByIdFromSlave(uid));
    }

    @PostMapping(value = "/")
    public Response<Long> insert(Order order)   throws JsonProcessingException {
        return ResultBuilder.builderResp(userService.insert(order));
    }


}
