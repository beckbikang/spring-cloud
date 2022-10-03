package cn.beckbi.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-02 15:59
 */
@ApiModel(value = "cn.beckbi.reponse", description = "id信息")
@Data
public class IdData {
    @ApiModelProperty(value="ID")
    private String id;
    @ApiModelProperty(value="UID")
    private Long uid;
}
