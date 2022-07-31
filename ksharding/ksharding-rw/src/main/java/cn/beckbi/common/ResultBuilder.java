package cn.beckbi.common;


import cn.beckbi.constants.ResultCode;
import cn.beckbi.entity.result.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:44
 */
@UtilityClass
public class ResultBuilder {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String builder(T data)  throws JsonProcessingException {
        Response<T> r = Response.<T>builder()
                .code(ResultCode.SUCCESS.getCode())
                .result(data)
                .message("成功")
                .build();
        return mapper.writeValueAsString(r);
    }

    public static <T> Response<T> builderResp(T data) {
        return Response.<T>builder()
                .code(ResultCode.SUCCESS.getCode())
                .result(data)
                .message(ResultCode.SUCCESS.name())
                .build();
    }


    public static <T> Response<T> builderFailed(ResultCode resultCode) {
        return Response.<T>builder()
                .code(resultCode.getCode())
                .message(resultCode.name())
                .build();
    }
}
