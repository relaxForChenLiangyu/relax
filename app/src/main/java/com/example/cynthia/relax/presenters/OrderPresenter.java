package com.example.cynthia.relax.presenters;

import android.os.Handler;
import com.example.cynthia.relax.activitis.login.LoginView;
import com.example.cynthia.relax.activitis.order.OrderView;
import com.example.cynthia.relax.beans.Type;
import com.example.cynthia.relax.services.IOrderService;
import com.example.cynthia.relax.services.IUserService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Date;
import java.util.List;

public class OrderPresenter {
    private OrderView orderView;
    private Handler mHandler;

    public OrderPresenter(OrderView orderView) {
        this.orderView = orderView;
        this.mHandler = new Handler();
    }

    public void setType(int specialistId) {
        IUserService userService = RetrofitServiceManager.getInstance().create(IUserService.class);
        Call<List<Integer>> call = userService.getTypes(Integer.toString(specialistId));
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                try {
                    List<Integer> gotTypes = response.body();
                    String[] type = new String[gotTypes.size()];
                    for (int i = 0; i < gotTypes.size(); i++)
                        type[i] = Type.getTypeByIndex(gotTypes.get(i));
                    orderView.displayTypes(type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
            }
        });
    }

    public void placeOrder(Integer userId, Integer specialistId, Integer selectedType, String Money, String description, Date dateStartTime, Date dateEndTime) {
        final IOrderService orderService = RetrofitServiceManager.getInstance().create(IOrderService.class);
        Call<Integer> call = orderService.placeOrder(String.valueOf(userId), String.valueOf(specialistId), String.valueOf(selectedType), Money, description, String.valueOf(dateStartTime.getTime()), String.valueOf(dateEndTime.getTime()));
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    int placeResult = response.body();
                    if (placeResult == 1)
                        orderView.showMsg("下单成功");
                    else
                        orderView.showMsg("下单失败");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                orderView.showMsg("连接错误");
            }
        });
    }
}
