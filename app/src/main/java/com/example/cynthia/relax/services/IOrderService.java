package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.OrderBean;
import com.example.cynthia.relax.utils.BaseJson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.ArrayList;
import java.util.List;

public interface IOrderService {
    @GET("order/getOrdersByPage")
    Call<List<OrderBean>> getOrdersByPage(@Query("userId") String userId, @Query("identity") String identity, @Query("page") String page);

    @GET("order/getOrderDetail")
    Call<OrderBean> getOrderDetail(@Query("orderId") String orderId);

    @GET("order/cancelOrder")
    Call<Integer> cancelOrder(@Query("orderId") String orderId);

    @GET("order/continueOrder")
    Call<Integer> continueOrder(@Query("orderId") String orderId);

    @GET("order/submitComment")
    Call<Integer> submitComment(@Query("orderId") String userId, @Query("rating") String identity, @Query("comment") String page);

    @GET("order/placeOrder")
    Call<Integer> placeOrder(@Query("userId") String userId, @Query("specialistId") String specialistId,
                             @Query("selectedType") String selectedType, @Query("money") String Money,
                             @Query("description") String description, @Query("startTime") String startTime,
                             @Query("endTime") String endTime);
}
