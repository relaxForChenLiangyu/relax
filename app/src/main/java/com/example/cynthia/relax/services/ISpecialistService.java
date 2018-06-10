package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.PreOrderStatusBean;
import com.example.cynthia.relax.beans.SpecialistBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ISpecialistService {
    @GET("specialist/getSortedSpecialistsByType")
    Call<List<SpecialistBean>> getSpecialists(@Query("type") Integer type,@Query("sort") Integer sort);

    @GET("specialist/getSpecialistBeanBySpecialistId")
    Call<SpecialistBean> getSpecialistBeanBySpecialistId(@Query("specialistId") Integer specialistId);

}
