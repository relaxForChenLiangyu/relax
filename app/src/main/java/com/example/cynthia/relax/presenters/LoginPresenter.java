package com.example.cynthia.relax.presenters;

import android.os.Handler;
import com.example.cynthia.relax.beans.User;
import com.example.cynthia.relax.models.ILoginModel;
import com.example.cynthia.relax.models.impl.LoginModelImpl;
import com.example.cynthia.relax.views.LoginView;

public class LoginPresenter {
    private LoginView loginView;
    private ILoginModel loginModel;
    private Handler mHandler;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
        mHandler = new Handler();
    }

    public void login() {
        loginView.showLoading();
        loginModel.login(loginView.getUserPhone(), loginView.getUserPassword(), new ILoginModel.OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //模拟登录成功后，返回信息到Activity,吐出成功信息
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showSuccessMsg(user);
                        loginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed(final String s) {
                //模拟登录失败后，返回信息，吐出错误信息
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showFailedMsg(s);
                        loginView.hideLoading();
                    }
                });
            }
        },loginView.context());
    }
}
