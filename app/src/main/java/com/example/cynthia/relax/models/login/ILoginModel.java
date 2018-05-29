package com.example.cynthia.relax.models.login;

import android.content.Context;
import com.example.cynthia.relax.beans.User;

public interface ILoginModel {
    void login(String phone, String password, OnLoginListener onLoginListener, Context context);

    interface OnLoginListener {
        void loginSuccess(User user);

        void loginFailed(String s);
    }
}
