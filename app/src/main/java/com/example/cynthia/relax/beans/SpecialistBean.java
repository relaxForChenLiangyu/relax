package com.example.cynthia.relax.beans;

import java.io.Serializable;

public class SpecialistBean implements Serializable {
    private String specialistName;
    private Integer specialistId;

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public Integer getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Integer specialistId) {
        this.specialistId = specialistId;
    }
}
