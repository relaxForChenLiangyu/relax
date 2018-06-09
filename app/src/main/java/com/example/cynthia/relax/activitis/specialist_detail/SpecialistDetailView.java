package com.example.cynthia.relax.activitis.specialist_detail;

import com.example.cynthia.relax.beans.CommentBean;
import com.example.cynthia.relax.beans.PreOrderStatusBean;

import java.util.List;

public interface SpecialistDetailView {
    void showFailedMsg(String msg);
    void setUserComments(List<CommentBean> userComments);
    void getSpecialistBeanBySpecialistId();
    void initTable(List<PreOrderStatusBean> preOrderStatusBeanList);
    void setNoCommentText(String msg);
}
