package com.ssm.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollectionUtil {
    /**
     * 检测Map是否为空
     *
     * @param map Map
     * @return true:empty, false: not empty
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.size() == 0);
    }

    /**
     * 检测Map是否不为空
     *
     * @param map Map
     * @return true: not empty, false: empty
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    /**
     * 检测集合是否为空
     *
     * @param collection Collection
     * @param <E>        AnyType
     * @return true:empty, false: not empty
     */
    public static <E> boolean isEmpty(Collection<E> collection) {
        return (collection == null || collection.size() == 0);
    }

    /**
     * 检测集合是否为空
     *
     * @param collection Collection
     * @param <E>        AnyType
     * @return true:not empty, false:empty
     */
    public static <E> boolean isNotEmpty(Collection<E> collection) {
        return !isEmpty(collection);
    }

    /**
     * 用map包装集合
     * 项目中的业务场景：一般在 controller 层返回时有此类包装需求
     *
     * @param key   AnyKey
     * @param value AnyValue
     * @return 包装后的hashMap HashMap<key,value>
     */
    public static <K, V> Map<K, V> wrapToMap(K key, V value) {
        Map<K, V> map = new HashMap<>(1);
        map.put(key, value);
        return map;
    }
}
