package com.example.cynthia.relax.activitis.post;

import android.content.Context;
import com.example.cynthia.relax.beans.PostBean;

import java.util.List;

public interface PostView {
    Context context();

    void setPostListData(List<PostBean> postBeans);
    void getPostListData();
    void showFailedMsg(String msg);
}
