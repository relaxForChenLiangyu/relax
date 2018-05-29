package com.example.cynthia.relax.utils;

import java.util.List;

/**
 * Created by 11022 on 2017/7/1.
 */
public class StringUtils {
    public static String join(List list, Character separator) {
        if (list == null || list.size() == 0) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : list) {
            stringBuilder.append(object.toString());
            stringBuilder.append(separator);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
