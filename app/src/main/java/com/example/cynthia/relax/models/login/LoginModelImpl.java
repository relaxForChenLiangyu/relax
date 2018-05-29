package com.example.cynthia.relax.models.login;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.cynthia.relax.beans.UserBean;
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
    protected static final String path = "http://10.0.2.2:10000/api/user/login";
    @Override
    public void login(final String phone, final String password,final OnLoginListener onLoginListener, Context context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(path)
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
                /*Call<UserBean> call = userService.getUserInfo(phone, password);
                call.enqueue(new Callback<UserBean>() {
                    @Override
                    public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                        System.out.println("!!!!!!!");
                        //onLoginListener.loginSuccess(new UserBean(phone,password));
                    }

                    @Override
                    public void onFailure(Call<UserBean> call, Throwable t) {
                        onLoginListener.loginFailed("账号或密码不正确");
                    }
                });*/
            }
        }.start();
    }
}
