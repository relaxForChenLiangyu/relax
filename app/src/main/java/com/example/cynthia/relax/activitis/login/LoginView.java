package com.example.cynthia.relax.activitis.login;

import android.content.Context;

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
    void showSuccessMsg(int userId);
    void showFailedMsg(String s);

    void saveUserIdAndIdentityToSharedPreferences(int userId,int identity);

}