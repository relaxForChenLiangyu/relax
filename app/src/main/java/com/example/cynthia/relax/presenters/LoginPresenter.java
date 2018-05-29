package com.example.cynthia.relax.presenters;

import android.os.Handler;
import com.example.cynthia.relax.beans.User;
import com.example.cynthia.relax.models.login.ILoginModel;
import com.example.cynthia.relax.models.login.LoginModelImpl;
import com.example.cynthia.relax.services.IUserService;
import com.example.cynthia.relax.activitis.login.LoginView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter {
    private LoginView loginView;
    private ILoginModel loginModel;
    private Handler mHandler;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
        this.mHandler = new Handler();
    }

    public void login(String phone,String password) {
        loginView.showLoading();

        String baseUrl = "http://10.0.2.2:10000/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IUserService userService = retrofit.create(IUserService.class);
        Call<User> call = userService.getUserInfo(phone, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loginView.showSuccessMsg(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginView.showFailedMsg("登录失败");
            }
        });
        /*loginModel.login(phone, password, new ILoginModel.OnLoginListener() {
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
        },loginView.context());*/
    }
}
