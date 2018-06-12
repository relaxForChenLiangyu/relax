package com.example.cynthia.relax.activitis.post;

import android.content.Context;
import com.example.cynthia.relax.beans.PostBean;
import com.example.cynthia.relax.beans.ReplyBean;

import java.util.List;

public interface PostDetailView {
    Context context();

    void showFailedMsg(String msg);
    void setPostBean(PostBean postBean);
    void getPostBeanByPostId();
    void getReplyListByPostId();
    void setReplyList(List<ReplyBean> replyList);
    void setNoReplyText(String msg);

    Integer sendReply(Integer toId,String content);
    void hideEdit();

}
