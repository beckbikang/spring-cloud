package cn.beckbi.controller;


import cn.beckbi.build.id.IdMaker;
import cn.beckbi.response.IdData;
import cn.beckbi.response.Result;
import cn.beckbi.service.GuavaCacheService;
import cn.beckbi.service.RedisCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-02-09 23:04
 */
@Api(tags={"用户接口"})
@RestController
public class UserController {

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private GuavaCacheService guavaCacheService;

    @ApiOperation(value = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name="uid",value="用户ID",dataType="long", paramType = "query", required=true, defaultValue="1")
    })
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Result.class) })
    @GetMapping("/user")
    public Result<IdData> info2(@RequestParam(name = "uid") long uid) {
        IdData idData = new IdData();
        idData.setUid(uid);
        idData.setId(guavaCacheService.getId(uid));
        return Result.<IdData>builder().code(0).result(idData).build();
    }


    @GetMapping("/user/{uid}")
    public IdData info(@PathVariable long uid) {
        IdData idData = new IdData();
        idData.setUid(uid);
        idData.setId(guavaCacheService.getId(uid));
        return idData;
    }

    @GetMapping("/user/s/{uid}")
    public IdData slaveInfo(@PathVariable long uid) {
        IdData idData = new IdData();
        idData.setUid(uid);
        idData.setId(redisCacheService.slaveGetId(uid));
        return idData;
    }

    @GetMapping("/user/m/{uid}")
    public IdData mastInfo(@PathVariable long uid) {
        IdData idData = new IdData();
        idData.setUid(uid);
        idData.setId(redisCacheService.masterGetId(uid));
        return idData;
    }
}
