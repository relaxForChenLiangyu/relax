package com.example.cynthia.relax.utils;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Iterator;

public class MyJsonObject {

    public static Object toBean(JSONObject jo, Class<?> cls) {
        Iterator<String> it = jo.keys();
        Object obj = null;
        try {
            obj = cls.newInstance();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        while (it.hasNext()) {
            try {
                String name = it.next();
                Object o = jo.opt(name);
                setter(obj, updateFirst(name), o, cls.getDeclaredField(name).getType());
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return obj;
    }

    private static void setter(Object obj, String att, Object value, Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }


    private static void getter(Object obj, String att) {
        try {
            Method method = obj.getClass().getMethod("get" + att);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private static String updateFirst(String fldName) {
        String first = fldName.substring(0, 1).toUpperCase();
        String rest = fldName.substring(1, fldName.length());
        String newStr = new StringBuffer(first).append(rest).toString();
        return newStr;
    }


}
