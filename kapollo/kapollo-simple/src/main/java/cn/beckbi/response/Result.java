package cn.beckbi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @program: spring-cloud
 * @description: result
 * @author: bikang
 * @create: 2022-02-09 23:10
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private Integer code;
    private T result;
    private String message;
    private String traceId;

}
