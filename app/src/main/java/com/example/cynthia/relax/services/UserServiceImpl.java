package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.utils.BaseJson;
import retrofit2.Call;

import java.util.List;

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

    @Override
    public Call<List<Integer>> getTypes(String specialistId) {
        return null;
    }
}
