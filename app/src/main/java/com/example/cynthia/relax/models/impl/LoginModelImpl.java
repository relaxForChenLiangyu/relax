package com.example.cynthia.relax.models.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.cynthia.relax.beans.User;
import com.example.cynthia.relax.models.ILoginModel;
import com.example.cynthia.relax.services.IUserService;
import com.example.cynthia.relax.services.UserServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModelImpl implements ILoginModel {
    IUserService userService;
    int loginResult;

    @Override
    public void login(final String phone, final String password,final OnLoginListener onLoginListener, Context context) {
        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserServiceImpl.class);

        final SharedPreferences user = context.getSharedPreferences("user", Context.MODE_APPEND);
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (phone.isEmpty()||password.isEmpty()){
                    onLoginListener.loginFailed("账号和密码不能为空");
                    return;
                }
                Call<User> call = userService.getUserInfo(phone, password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        onLoginListener.loginSuccess(new User(phone,password));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        onLoginListener.loginFailed("账号或密码不正确");
                    }
                });
            }
        }.start();
    }
}
