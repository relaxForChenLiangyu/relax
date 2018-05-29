package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.User;
import retrofit2.Call;

public class UserServiceImpl implements IUserService{

    @Override
    public Call<User> getUserInfo(String phone, String password) {

        return null;
    }
}
