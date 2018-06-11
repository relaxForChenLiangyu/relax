package com.example.cynthia.relax.activitis.historyorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.orderdetail.OrderDetailActivity;
import com.example.cynthia.relax.beans.OrderBean;
import com.example.cynthia.relax.beans.OrderStatus;
import com.example.cynthia.relax.beans.Type;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.ViewHolder> {
    private List<OrderBean> mOrderList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView consultingType;
        TextView orderStatus;
        TextView partnerName;
        TextView sum;
        Button operateButton;

        public ViewHolder(View view) {
            super(view);
            consultingType = (TextView) view.findViewById(R.id.consultingType);
            orderStatus = (TextView) view.findViewById(R.id.orderStatus);
            partnerName = (TextView) view.findViewById(R.id.partnerName);
            sum = (TextView) view.findViewById(R.id.sum);
            operateButton = (Button) view.findViewById(R.id.changeOrderStatus);
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
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OrderBean orderBean = mOrderList.get(position);
        holder.consultingType.setText(Type.getTypeByIndex(orderBean.getTypeId()));
        holder.orderStatus.setText(OrderStatus.getStatusByIndex(orderBean.getOrderStatus()));
        holder.partnerName.setText(orderBean.getPartnerName());
        holder.sum.setText(orderBean.getSum().toString());
        holder.operateButton.setText("订单详情/操作");
        holder.operateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("orderId", orderBean.getOrderId().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }
}
