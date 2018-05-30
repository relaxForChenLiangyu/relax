package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.UserBean;
import retrofit2.Call;

public class UserServiceImpl implements IUserService{

    @Override
    public Call<Integer> getUserInfo(String phone, String password) {

        return null;
    }

    public Call<Integer> register(String nickname, String realname, String phone, String password) {

        return null;
    }

    @Override
    public Call<Integer> getUserId(String phone, String password) {
        return null;
    }
}
