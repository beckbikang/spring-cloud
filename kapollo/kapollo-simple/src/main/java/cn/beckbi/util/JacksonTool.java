package cn.beckbi.util;

import lombok.experimental.UtilityClass;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-10-03 16:56
 */
@UtilityClass
public class JacksonTool {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        /**
         * 允许pojo中有在json串中不存在的字段
         */
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        /**
         * 允许有注释
         */
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }



    public static <T> T parseObject(String json, Class<T> tClass){
        try {
            return objectMapper.readValue(json,tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJSONString(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T>List<T> json2List(String json, Class<T> tClass) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, tClass);
        try {
            List<T> list = objectMapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T>T json2Object(String json,Class<T> outClass,Class inClass) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(outClass, inClass);
        try {
            T obj = objectMapper.readValue(json, javaType);
            return obj;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> json2Map(String json, Class<K> keyType, Class<V> valueType) {
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        try {
            Map<K, V> resultMap = objectMapper.readValue(json, javaType);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
