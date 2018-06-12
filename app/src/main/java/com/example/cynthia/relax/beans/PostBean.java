package com.example.cynthia.relax.beans;

import java.io.Serializable;
import java.util.Date;

public class PostBean implements Serializable {
    private Integer postId;

    private Integer userId;

    private String title;

    private String content;

    private String userName;

    private String time;

    public PostBean() {}

    public PostBean(Integer postId, Integer userId, String title, String content, String userName, String time) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.time = time;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
