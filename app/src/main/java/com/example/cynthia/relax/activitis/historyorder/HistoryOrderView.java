package com.example.cynthia.relax.activitis.historyorder;

import android.content.Context;
import com.example.cynthia.relax.beans.OrderBean;

import java.util.ArrayList;
import java.util.List;

public interface HistoryOrderView {
    void showLoading();
    void hideLoading();
    void showFailedMsg(String s);
    void showSuccessMsg(String s);
    void refreshOrderList(List<OrderBean> addition);
    void changeStatus() ;
}
