package com.hex.bigdata.udsp.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class JSONUtil {

    public static <T> String parseObj2JSON(T obj) {
        if (obj == null) {
            return "";
        }
        JSONObject jsonObject = JSONObject.fromObject (obj);
        return jsonObject.toString ();
    }

    public static <T> T parseJSON2Obj(String jsonStr, Class clazz) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        return (T) JSONObject.toBean (jsonObject, clazz);
    }

    public static <T> String parseList2JSON(List<T> objList) {
        if (objList == null) {
            return "";
        }
        JSONArray jsonArray = JSONArray.fromObject (objList);
        return jsonArray.toString ();
    }

    public static <T> List<T> parseJSON2List(String jsonStr, Class clazz) {
        JSONArray jsonArray = JSONArray.fromObject (jsonStr);
        return (List<T>) JSONArray.toCollection (jsonArray, clazz);
    }

    public static <T> String parseMap2JSON(Map<String, T> map) {
        if (map == null) {
            return "";
        }
        JSONObject jsonObject = JSONObject.fromObject (map);
        return jsonObject.toString ();
    }

    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        HashMap<String, Object> map = new HashMap<> ();
        Iterator var = jsonObject.keySet ().iterator ();
        while (var.hasNext ()) {
            Object key = var.next ();
            Object val = jsonObject.get (key);
            if (val instanceof JSONArray) {
                ArrayList<Map<String, Object>> list = new ArrayList<> ();
                Iterator it = ((JSONArray) val).iterator ();
                while (it.hasNext ()) {
                    JSONObject jsonObj = (JSONObject) it.next ();
                    list.add (parseJSON2Map (jsonObj.toString ()));
                }
                map.put (key.toString (), list);
            } else {
                map.put (key.toString (), val);
            }
        }
        return map;
    }

    public static <T> Map<String, T> parseJSON2Map(String jsonStr, Class clazz) {
        HashMap map = new HashMap ();
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        Iterator var = jsonObject.keySet ().iterator ();
        while (var.hasNext ()) {
            Object key = var.next ();
            Object val = jsonObject.get (key);
            if (val instanceof JSONArray) {
                ArrayList list = new ArrayList ();
                Iterator it = ((JSONArray) val).iterator ();
                while (it.hasNext ()) {
                    JSONObject jsonObj = (JSONObject) it.next ();
                    list.add (parseJSON2Map (jsonObj.toString (), clazz));
                }
                map.put (key.toString (), list);
            } else if (val instanceof JSONObject) {
                map.put (key.toString (), JSONObject.toBean ((JSONObject) val, clazz));
            } else {
                map.put (key.toString (), val);
            }
        }
        return map;
    }

    public static String parseMapList2JSON(List<Map<String, Object>> mapList) {
        if (mapList == null) {
            return "";
        }
        JSONArray jsonArray = JSONArray.fromObject (mapList);
        return jsonArray.toString ();
    }

    public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
        JSONArray jsonArray = JSONArray.fromObject (jsonStr);
        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>> ();
        Iterator it = jsonArray.iterator ();
        while (it.hasNext ()) {
            JSONObject jsonObject = (JSONObject) it.next ();
            mapList.add (parseJSON2Map (jsonObject.toString ()));
        }
        return mapList;
    }

    public static <T> T parseJSON2Obj(String jsonStr, Class clazz, Map<String, Class> classMap) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        return (T) JSONObject.toBean (jsonObject, clazz, classMap);
    }

    public static Object parseJSON2SubObj(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        return jsonObject.get (key);
    }

    public static String parseJSON2SubString(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        return jsonObject.getString (key);
    }

    public static boolean parseJSON2SubBoolean(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        return jsonObject.getBoolean (key);
    }

    public static double parseJSON2SubDouble(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        return jsonObject.getDouble (key);
    }

    public static int parseJSON2SubInt(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        return jsonObject.getInt (key);
    }

    public static long parseJSON2SubLong(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        return jsonObject.getLong (key);
    }

    public static Map<String, Object> parseJSON2SubMap(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        JSONObject jsonObj = jsonObject.getJSONObject (key);
        return parseJSON2Map (jsonObj.toString ());
    }

    public static List<Map<String, Object>> parseJSON2SubList(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject (jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray (key);
        return parseJSON2List (jsonArray.toString ());
    }
}
