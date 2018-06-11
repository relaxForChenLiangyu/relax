package com.example.cynthia.relax.activitis.personal;

import com.example.cynthia.relax.beans.CommentBean;

import java.util.List;

public interface PersonalCommentsView {
    void showFailedMsg(String msg);
    void bindCommentData(List<CommentBean> commentBeanList);
    //void setRatingText(Double rating);
}
