package com.example.cynthia.relax.activitis.order;

import java.text.ParseException;

public interface OrderView {
    void displayTypes(String[] types);
    void setStartTimes();
    void setEndTimes(String[] endTimes);
    void formatStartTime(String strStart) throws ParseException;
    void formatEndTime(String strEnd) throws ParseException;
    void setType(String setType);
    void showMsg(String message);
}
