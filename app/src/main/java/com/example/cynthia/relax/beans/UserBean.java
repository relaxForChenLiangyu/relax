package com.example.cynthia.relax.beans;


import org.json.JSONException;
import org.json.JSONObject;

public class UserBean {
    private Integer userId;

    private String nickname;

    private String phone;

    private String password;

    private String realName;

    private Integer identity;

    private Double remainder;

    private String portrait;

    private Double relaxDegree;

    public UserBean(){

    }

    public UserBean(Integer userId,String phone, String password) {
        this.userId = userId;
        this.nickname = "";
        this.phone = phone;
        this.password = password;
        this.realName = "";
        this.identity = 0;
        this.remainder = 0.0;
        this.portrait = "";
        this.relaxDegree = 80.0;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Double getRemainder() {
        return remainder;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Double getRelaxDegree() {
        return relaxDegree;
    }

    public void setRelaxDegree(Double relaxDegree) {
        this.relaxDegree = relaxDegree;
    }
}
