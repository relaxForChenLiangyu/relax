package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.utils.BaseJson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IUserService {
    @GET("user/login")
    Call<BaseJson> getUserInfo(@Query("phone") String phone, @Query("password") String password);

    @GET("user/getUserId")
    Call<Integer> getUserId(@Query("phone") String phone, @Query("password") String password);
}
