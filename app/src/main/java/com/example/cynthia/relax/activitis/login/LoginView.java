package com.example.cynthia.relax.activitis.login;

import android.content.Context;
import com.example.cynthia.relax.beans.User;

/**
 * Created by Administrator on 2018/4/29/029.
 */

public interface LoginView {
    //得到用户填写的信息
    String getUserPhone();
    String getUserPassword();

    Context context();
    //显示和隐藏登录ProgressBar
    void showLoading();
    void hideLoading();

    //登录成功或失败后，返回信息的方法
    void showSuccessMsg(User user);
    void showFailedMsg(String s);

}