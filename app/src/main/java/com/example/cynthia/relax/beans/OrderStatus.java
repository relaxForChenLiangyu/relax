package com.example.cynthia.relax.beans;

public enum OrderStatus {
    APPLYING("等待专家接单", 1),
    CANCELLED("已取消", 2),
    CHECKED("待付款", 3),
    PAID("待咨询", 4),
    PAYBACKAPPLYING("退款处理中", 5),
    PAIDBACK("已退款", 6),
    CONSULTING("咨询中", 7),
    COMPLETED("待评价", 8),
    FINISHED("已完成", 9);
    private String title;
    private int index;

    private OrderStatus(String title, int index) {
        this.title = title;
        this.index = index;
    }

    public static String getStatusByIndex(int index) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (index == orderStatus.getIndex()) {
                return orderStatus.toString();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public int getIndex() {
        return this.index;
    }

    public Integer parseInt() {
        return this.index;
    }
}
