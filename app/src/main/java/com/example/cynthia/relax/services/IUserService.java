package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IUserService {
    @GET("user/login")
    Call<User> getUserInfo(@Query("phone") String phone, @Query("password") String password);
}
