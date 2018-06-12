package com.example.cynthia.relax.beans;

import java.io.Serializable;

public class MessageBean implements Serializable {

    private int type;
    private int msgId;
    private int receiverId;
    private int senderId;
    private String content;
    private String createTime;


    public MessageBean(){

    }
    public MessageBean(String context, int type){
        this.content = context;
        this.type = type;
    }
    public MessageBean(String data, int number,String time) {
        this.content = data;
        this.type = number;
        this.createTime = time;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
