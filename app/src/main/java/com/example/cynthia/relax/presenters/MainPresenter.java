package com.example.cynthia.relax.presenters;

import com.example.cynthia.relax.activitis.main.MainView;
import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.services.IUserService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void getCurrentUserInfo(int userId) {
        IUserService userService = RetrofitServiceManager.getInstance().create(IUserService.class);
        if(userId==0)
            mainView.showFailedMsg("您还没有登录_(:3」∠)_");
        else {
            Call<UserBean> call = userService.getCurrentUserInfo(userId);
            call.enqueue(new Callback<UserBean>() {
                @Override
                public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                    try {
                        UserBean userBean = response.body();
                        if (userBean != null)
                            mainView.setUserBean(userBean);
                        else
                            mainView.showFailedMsg("我们没有找到您的个人信息_(:3」∠)_");

                    } catch (Exception e) {
                        mainView.showFailedMsg("网络连接错误");
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<UserBean> call, Throwable t) {
                    mainView.showFailedMsg("加载个人信息失败");
                }
            });
        }
    }
}
