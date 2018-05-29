package com.example.cynthia.relax.models.login;

import android.content.Context;
import com.example.cynthia.relax.beans.UserBean;

public interface ILoginModel {
    void login(String phone, String password, OnLoginListener onLoginListener, Context context);

    interface OnLoginListener {
        void loginSuccess(UserBean userBean);

        void loginFailed(String s);
    }
}
