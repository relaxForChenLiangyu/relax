package com.example.cynthia.relax.beans;

public enum Type {
    MARRIAGE("婚恋", 1),
    PARENTING("亲子", 2),
    WORKING("职场", 3),
    HEALTHY("健康", 4);

    private String title;
    private int index;

    private Type(String title, int index) {
        this.title = title;
        this.index = index;
    }

    public static String getTypeByIndex(int index) {
        for (Type type : Type.values()) {
            if (index == type.getIndex()) {
                return type.toString();
            }
        }
        return null;
    }
    public static Integer getTypeByTitle(String title) {
        for (Type type : Type.values()) {
            if (title == type.toString()) {
                return type.getIndex();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public int getIndex() {
        return index;
    }

    public Integer parseInt() {
        return this.index;
    }
}
