package com.example.cynthia.relax.activitis.post;

import android.content.Context;
import com.example.cynthia.relax.beans.ReplyBean;

import java.util.List;

public interface MoreReplyView {
    Context context();

    void showFailedMsg(String msg);
    void setReplyBean(ReplyBean replyBean);
    void getReplyBeanByReplyId();
    void getReplyListByReplyId();
    void setReplyList(List<ReplyBean> replyList);

    Integer sendReply(Integer toId,String content);
    void hideEdit();

}
