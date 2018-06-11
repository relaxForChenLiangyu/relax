package com.example.cynthia.relax.beans;

import java.io.Serializable;

public class TypeBean implements Serializable{
    private Integer typeId;

    private String title;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
