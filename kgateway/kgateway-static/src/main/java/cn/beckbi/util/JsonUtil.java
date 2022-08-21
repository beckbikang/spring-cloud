package cn.beckbi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-21 21:37
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String getJsonStr(Object o) {
        try {
            return mapper.writeValueAsString(o);
        }catch (JsonProcessingException e) {
            log.error("json error", e);
        }
        return "{}";
    }

    public static byte[] getJsonBytes(Object o) {
        return getJsonStr(o).getBytes();
    }
}
