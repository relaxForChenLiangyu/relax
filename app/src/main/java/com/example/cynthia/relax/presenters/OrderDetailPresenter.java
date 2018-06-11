package com.example.cynthia.relax.presenters;

import android.os.Handler;
import com.example.cynthia.relax.activitis.login.LoginView;
import com.example.cynthia.relax.activitis.orderdetail.OrderDetailView;
import com.example.cynthia.relax.beans.OrderBean;
import com.example.cynthia.relax.services.IOrderService;
import com.example.cynthia.relax.services.IUserService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailPresenter {
    private OrderDetailView orderDetailView;
    private Handler mHandler;

    public OrderDetailPresenter(OrderDetailView orderDetailView) {
        this.orderDetailView = orderDetailView;
        this.mHandler = new Handler();
    }

    public void showOrder(String orderId) {
        IOrderService orderService = RetrofitServiceManager.getInstance().create(IOrderService.class);
        Call<OrderBean> call = orderService.getOrderDetail(orderId);
        call.enqueue(new Callback<OrderBean>() {
            @Override
            public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {
                try {
                    OrderBean orderBean = response.body();
                    orderDetailView.showDatas(orderBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<OrderBean> call, Throwable t) {
                orderDetailView.showMsg("获取失败");
            }
        });
    }

    public void cancelOrder(String orderId) {
        IOrderService orderService = RetrofitServiceManager.getInstance().create(IOrderService.class);
        Call<Integer> call = orderService.cancelOrder(orderId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    Integer result = response.body();
                    if (result == 1)
                        orderDetailView.showMsg("取消成功");
                    else
                        orderDetailView.showMsg("订单取消失败");
                    orderDetailView.statusChangedIntent();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                orderDetailView.showMsg("获取失败");
            }
        });
    }

    public void continueOrder(String orderId) {
        IOrderService orderService = RetrofitServiceManager.getInstance().create(IOrderService.class);
        Call<Integer> call = orderService.continueOrder(orderId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    Integer result = response.body();
                    if (result == 1)
                        orderDetailView.showMsg("订单更改状态成功");
                    else
                        orderDetailView.showMsg("订单更改状态失败");
                    //orderDetailView.statusChangedIntent();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                orderDetailView.showMsg("获取失败");
            }
        });
    }
}
