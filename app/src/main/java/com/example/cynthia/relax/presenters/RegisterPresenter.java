package com.example.cynthia.relax.presenters;

import android.os.Handler;
import com.example.cynthia.relax.activitis.register.RegisterView;
import com.example.cynthia.relax.services.IUserService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private RegisterView registerView;
    private Handler mHandler;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
        this.mHandler = new Handler();
    }

    public void register(String nickname, String realname, String phone, String password) {
        registerView.showLoading();
        IUserService userService = RetrofitServiceManager.getInstance().create(IUserService.class);
        Call<Integer> call = userService.register(nickname,realname,phone,password);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response){
                try {
                    int userId = response.body();
                    registerView.saveUserIdToSharedPreferences(userId);
                    registerView.showSuccessMsg(userId);
                    registerView.hideLoading();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                registerView.showFailedMsg("注册失败");
                registerView.hideLoading();
            }
        });
    }
}
