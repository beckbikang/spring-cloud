package cn.beckbi.controller;


import cn.beckbi.common.ResultBuilder;
import cn.beckbi.entity.result.Response;
import cn.beckbi.model.User;
import cn.beckbi.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:44
 */
@Slf4j
@RequestMapping(value="api/v1/user",produces = "application/json;charset=utf-8")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/db1/{id}")
    public Response<User> getMasterUser1(@PathVariable("id") Long id)   throws JsonProcessingException {
        return ResultBuilder.builderResp(userService.getByIdFromMaster(id));
    }

    @GetMapping(value = "/db11/{id}")
    public Response<User> getMaster2User1(@PathVariable("id") Long id)   throws JsonProcessingException {
        return ResultBuilder.builderResp(userService.getByIdFromMaster2(id));
    }

    @GetMapping(value = "/db2/{id}")
    public Response<User> getSlaveUser2(@PathVariable("id") Long id)   throws JsonProcessingException {
        return ResultBuilder.builderResp(userService.getByIdFromSlave(id));
    }
}
