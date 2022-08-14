package cn.beckbi.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-13 11:30
 */
@Slf4j
@UtilityClass
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getJsonStr(Object o) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("json serialize failed", e);
        }
        return "";
    }
}
