package com.example.cynthia.relax.activitis.historyorder;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.OrderBean;
import com.example.cynthia.relax.presenters.HistoryOrderPresenter;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;

public class HistoryOrderActivity extends AppCompatActivity implements HistoryOrderView {
    HistoryOrderPresenter historyOrderPresenter;
    public List<OrderBean> orderBeans = new ArrayList<>();
    int page = 0;
    int orderSum = 0;
    boolean finished = false;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    HistoryOrderAdapter historyOrderAdapter;
    Integer userId;
    Integer identity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);
        historyOrderPresenter = new HistoryOrderPresenter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.orders_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        historyOrderAdapter = new HistoryOrderAdapter(orderBeans,HistoryOrderActivity.this);
        recyclerView.setAdapter(historyOrderAdapter);
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId",1);
        identity = sharedPreferences.getInt("identity", 0);
        historyOrderPresenter.getHistoryOrdersByPage(userId, identity, page);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_DRAGGING && !recyclerView.canScrollVertically(1) && recyclerView.canScrollVertically(-1) && !finished) {
                    historyOrderPresenter.getHistoryOrdersByPage(userId, identity, page);
                }
            }
            /*
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }*/
        });
    }

    public void showSuccessMsg(String s) {
        Toast.makeText(HistoryOrderActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedMsg(String s) {
        Toast.makeText(HistoryOrderActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void refreshOrderList(List<OrderBean> addition) {
        if (addition != null)
            for (int i = 0; i < addition.size(); i++)
                orderBeans.add(addition.get(i));
    }

    @Override
    public void changeStatus() {
        if (orderSum != orderBeans.size())
            page++;
        else
            finished = true;
        orderSum = orderBeans.size();
        historyOrderAdapter.notifyDataSetChanged();
    }
}
