package cn.beckbi.user.controller;

import cn.beckbi.user.dto.UserInfo;
import cn.beckbi.user.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-02-09 23:04
 */
@RestController
public class UserController {

    @GetMapping("/user/{uid}")
    public Result<UserInfo> info(@PathVariable long uid) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(123L);
        userInfo.setAge(21);
        userInfo.setName("tom");
        userInfo.setHeight(183.4f);
        return Result.<UserInfo>builder().code(0).result(userInfo).build();
    }
}
