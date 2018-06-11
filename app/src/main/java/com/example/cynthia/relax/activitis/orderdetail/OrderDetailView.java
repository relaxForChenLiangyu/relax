package com.example.cynthia.relax.activitis.orderdetail;

import com.example.cynthia.relax.beans.OrderBean;

public interface OrderDetailView {
    public void showDatas(OrderBean orderBean);
    public void showMsg(String message);
    public void statusChangedIntent();
}
