package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.PreOrderStatusBean;
import com.example.cynthia.relax.beans.SpecialistBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

public interface ISpecialistService {
    @GET("specialist/getSortedSpecialistsByType")
    Call<List<SpecialistBean>> getSpecialists(@Query("type") Integer type,@Query("sort") Integer sort);

    @GET("specialist/getSpecialistBeanBySpecialistId")
    Call<SpecialistBean> getSpecialistBeanBySpecialistId(@Query("specialistId") Integer specialistId);

    @GET("specialist/submitQualification")
    Call<Integer> submitQualification(@QueryMap Map<String,Object> resume);
}
