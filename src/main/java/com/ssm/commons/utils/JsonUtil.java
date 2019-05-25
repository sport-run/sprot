package com.ssm.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private JsonUtil() {
    }

    /**
     * JAVA对象转换成json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString(obj);
    }

    /**
     * json字符串转换成Map对象
     *
     * @param json 字符串
     * @return Map结果
     */
    public static Map<String, String> toMap(String json) {
        return JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
    }

    /**
     * json转换成对象
     *
     * @param json
     * @return
     */
    public static <T> T toObject(Class<T> dest, String json) {
        return JSON.parseObject(json, dest);
    }

    /**
     * json转换成对象
     *
     * @param json Json
     * @return 嵌套对象
     */
    public static <T> T toObject(String json, TypeReference<T> type, Feature... features) {
        return JSON.parseObject(json, type, features);
    }

    /**
     * json转换为List对象
     *
     * @param dest List内部对象
     * @param json
     * @return
     */
    public static <T> List<T> toList(Class<T> dest, String json) {
        List list = JSON.parseObject(json, List.class);
        List<T> result = new ArrayList<T>();
        //进行非空的判断
        if (list == null || list.size() == 0) {
            return result;
        }
        for (Object obj : list) {
            result.add(JSON.parseObject(obj.toString(), dest));
        }
        return result;
    }

    /**
     * 校验json串是否合法
     *
     * @param source
     * @return
     */
    public static boolean validateJson(String source) {
        source = source.replaceAll("@type", "abc");  //add by zhuJinJin 2017-06-08   剔除@type 否则报错：autoType is not support
        try {
            JSON.parse(source);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    /**
     * java 对象转为 map，如果属性加了 JSONField 属性，则会按照其定义的 name 设定 map 的 key
     *
     * @param object
     * @return
     */
    public static Map<String, String> objectStringMap(Object object) {
        String json = toJson(object);
        if (json == null) {
            return null;
        }
        Map<String, String> stringMap = toMap(json);
        return stringMap;
    }
}
