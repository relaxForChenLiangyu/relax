package com.example.cynthia.relax.activitis.historyorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.orderdetail.OrderDetailActivity;
import com.example.cynthia.relax.activitis.specialist_detail.SpecialistDetailActivity;
import com.example.cynthia.relax.beans.OrderBean;
import com.example.cynthia.relax.beans.OrderStatus;
import com.example.cynthia.relax.beans.Type;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.ViewHolder> implements View.OnClickListener {
    private List<OrderBean> mOrderList;
    private Context context;

    @Override
    public void onClick(View v) {
        skip(mOrderList.get((int)v.getTag()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView consultingType;
        TextView orderStatus;
        TextView partnerName;
        TextView sum;
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            consultingType = (TextView) view.findViewById(R.id.consultingType);
            orderStatus = (TextView) view.findViewById(R.id.orderStatus);
            partnerName = (TextView) view.findViewById(R.id.partnerName);
            sum = (TextView) view.findViewById(R.id.sum);
        }
    }

    public HistoryOrderAdapter(List<OrderBean> orderBeans, Context context) {
        mOrderList = orderBeans;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OrderBean orderBean = mOrderList.get(position);
        holder.view.setTag(position);
        holder.consultingType.setText(Type.getTypeByIndex(orderBean.getTypeId()));
        holder.orderStatus.setText(OrderStatus.getStatusByIndex(orderBean.getOrderStatus()));
        holder.partnerName.setText(orderBean.getPartnerName());
        holder.sum.setText(orderBean.getSum().toString());
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    public void skip(OrderBean orderBean){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("orderBean", orderBean);
        intent.putExtra("orderId", orderBean.getOrderId());
        intent.putExtras(bundle);
        intent.setClass(context, OrderDetailActivity.class);
        startActivity(intent);
    }

    public void startActivity(Intent intent) {
        this.context.startActivity(intent);
    }
}
