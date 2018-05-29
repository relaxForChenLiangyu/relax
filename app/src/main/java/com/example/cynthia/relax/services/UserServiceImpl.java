package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.utils.BaseJson;
import retrofit2.Call;

public class UserServiceImpl implements IUserService{

    @Override
    public Call<BaseJson> getUserInfo(String phone, String password) {

        return null;
    }

    @Override
    public Call<Integer> getUserId(String phone, String password) {
        return null;
    }
}
