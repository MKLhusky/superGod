package com.system.supercommon.util.spring;

import com.alibaba.fastjson2.JSON;

import java.util.List;
import java.util.Map;

public class JsonSerializableUtil {

    public static byte[] objToByteArray(Object obj){
        return JSON.toJSONBytes(obj);
    }

    public static byte[] mapToByteArray(Map map){
        return JSON.toJSONBytes(map);
    }

    public static String mapToString(Map map){
        return JSON.toJSONString(map);
    }

    public static byte[] listToByteArray(List list){
        return JSON.toJSONBytes(list);
    }

    public static String listToString(List list){
        return JSON.toJSONString(list);
    }
}
