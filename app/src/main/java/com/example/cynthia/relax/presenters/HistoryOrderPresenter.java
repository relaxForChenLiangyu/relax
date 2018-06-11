package com.example.cynthia.relax.presenters;

import android.os.Handler;
import com.example.cynthia.relax.activitis.historyorder.HistoryOrderView;
import com.example.cynthia.relax.beans.OrderBean;
import com.example.cynthia.relax.services.IOrderService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class HistoryOrderPresenter {
    private HistoryOrderView historyOrderView;
    private Handler mHandler;

    public HistoryOrderPresenter(HistoryOrderView historyOrderView) {
        this.historyOrderView = historyOrderView;
        this.mHandler = new Handler();
    }

    public void getHistoryOrdersByPage(Integer userId, Integer identity, int page) {
        IOrderService orderService = RetrofitServiceManager.getInstance().create(IOrderService.class);
        Call<List<OrderBean>> call = orderService.getOrdersByPage(userId.toString(), identity.toString(), String.valueOf(page));
        call.enqueue(new Callback<List<OrderBean>>() {
            @Override
            public void onResponse(Call<List<OrderBean>> call, Response<List<OrderBean>> response) {
                try {
                    List<OrderBean> orderBeans = response.body();
                    if (orderBeans == null) {
                        historyOrderView.showSuccessMsg("已无更多订单！");
                    } else {
                        historyOrderView.refreshOrderList(orderBeans);
                        historyOrderView.changeStatus();
                        historyOrderView.showSuccessMsg("加载成功");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<OrderBean>> call, Throwable t) {
                historyOrderView.showFailedMsg("网络故障");
            }
        });
    }
}
