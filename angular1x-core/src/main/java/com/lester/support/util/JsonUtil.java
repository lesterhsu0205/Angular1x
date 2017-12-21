package com.lester.support.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtil {

    private static Gson gson = null;

    private static final Logger LOGGER = Logger.getLogger(JsonUtil.class);
    private static final boolean TRACE_LOG = false;

    // singleton
    private static void initGson() {
        if (gson == null) {
            synchronized (JsonUtil.class) {
                if (gson == null) {
                    gson = new GsonBuilder().setDateFormat(DateUtil.FORMAT_Date).create();
                }
            }
        }
    }

    public static <T> String toJson(T t) {
        initGson();
        return gson.toJson(t);
    }

    public static <T> T fromJson(Class<T> clazz, String json) {
        initGson();
        return gson.fromJson(json, clazz);
    }


    /**
     * JSON Object format to java Map.
     *
     * @param jObj JSONObject
     */
    public static Map<String, String> jsonObjToMap(final JSONObject jObj) {
        final Map<String, String> resultMap = new HashMap<String, String>();

        try {
            for (Iterator<String> itObj = jObj.keys(); itObj.hasNext(); ) {
                String key = itObj.next();
                resultMap.put(key, jObj.getString(key));
            }
        } catch (Exception e) {
            if (TRACE_LOG) {
                LOGGER.info("ConvertUtils.jsonObjToMap()", e);
            }
        }

        return resultMap;
    }

}
