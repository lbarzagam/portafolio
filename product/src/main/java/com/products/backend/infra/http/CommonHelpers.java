package com.products.backend.infra.http;

import java.util.HashMap;
import java.util.Map;

public class CommonHelpers {

    public static Object getFromMap(String key_, Map<String, Object> dataMap) {
        return getFromMap(key_, dataMap, null);
    }

    public static Object getFromMap(String key_, Map<String, Object> dataMap, Object default_) {
        return (dataMap.get(key_) != null) ? dataMap.get(key_) : default_;
    }

    public static Map<String, Object> hashMapFromMap(String key_, Map<String, Object> dataMap) {
        return (HashMap<String, Object>) getFromMap(key_, dataMap, new HashMap<String, Object>());
    }
}