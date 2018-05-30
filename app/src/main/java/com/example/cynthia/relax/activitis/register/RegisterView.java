package com.example.cynthia.relax.activitis.register;

import android.content.Context;

public interface RegisterView {
    //得到用户填写的信息
    String getUserName();
    String getUserRealName();
    String getUserPhone();
    String getUserPassword();

    Context context();
    //显示和隐藏登录ProgressBar
    void showLoading();
    void hideLoading();

    //登录成功或失败后，返回信息的方法
    void showSuccessMsg(int userId);
    void showFailedMsg(String s);

    void saveUserIdToSharedPreferences(int userId);
}
