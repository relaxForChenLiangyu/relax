package com.example.cynthia.relax.beans;

import java.io.Serializable;
import java.util.Date;

public class MessageBean implements Serializable {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private int type;
    public int getType() {
        return type;
    }

    public MessageBean(String context, int type){
        this.content = context;
        this.type = type;
    }
    public void setType(int type) {
        this.type = type;
    }

    private int msgId;
    private int receiverId;
    private int senderId;
    private String content;
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
