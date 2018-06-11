package com.example.cynthia.relax.activitis.personal;

import com.example.cynthia.relax.beans.SpecialistBean;

public interface PersonalView {
    void showFailedMsg(String msg);
    void bindSpecialistData(SpecialistBean specialistBean);
}
