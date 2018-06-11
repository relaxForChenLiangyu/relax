package com.example.cynthia.relax.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * File name：BaseJson
 * Author: Administrator
 * Description：JSON基础结构，包含响应码和响应消息，反馈给前台页面
 * Modify History:
 */
public class BaseJson implements Serializable, Cloneable {

    private String returnCode;//响应代码
    private String errorMessage;//错误消息

    private Object obj;


    public BaseJson(JSONObject jsonObject) throws JSONException {
        this.returnCode = jsonObject.getString("returnCode");
        this.errorMessage = jsonObject.getString("errorMessage");
        try{
            this.obj = jsonObject.getJSONObject("obj");
        }catch (JSONException e){
            this.obj = jsonObject.getJSONArray("obj");
        }
    }

    public BaseJson(String jsonObject) throws JSONException {
        this(new JSONObject(jsonObject));
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public JSONObject getJSONObject() {
        return (JSONObject) obj;
    }

    public JSONArray getJSONArray() {
        return (JSONArray) obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
