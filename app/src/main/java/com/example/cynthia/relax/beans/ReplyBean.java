package com.example.cynthia.relax.beans;

import java.io.Serializable;

public class ReplyBean implements Serializable {
    private Integer postReplyId;

    private Integer userId;

    private Integer postId;

    private Integer reReplyId;

    private String replyTime;

    private String content;

    private Integer replyNum;

    private String userName;

    public ReplyBean() {
    }

    public ReplyBean(Integer postReplyId, Integer userId, Integer postId, Integer reReplyId, String replyTime, String content, Integer replyNum, String userName) {
        this.postReplyId = postReplyId;
        this.userId = userId;
        this.postId = postId;
        this.reReplyId = reReplyId;
        this.replyTime = replyTime;
        this.content = content;
        this.replyNum = replyNum;
        this.userName = userName;
    }

    public Integer getPostReplyId() {
        return postReplyId;
    }

    public void setPostReplyId(Integer postReplyId) {
        this.postReplyId = postReplyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getReReplyId() {
        return reReplyId;
    }

    public void setReReplyId(Integer reReplyId) {
        this.reReplyId = reReplyId;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
