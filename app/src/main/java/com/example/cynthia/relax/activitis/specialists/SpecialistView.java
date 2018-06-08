package com.example.cynthia.relax.activitis.specialists;

import android.content.Context;
import com.example.cynthia.relax.beans.SpecialistBean;

import java.util.List;

public interface SpecialistView {
    Context context();

    void setSpecialistData(List<SpecialistBean> specialistBeans);
    void getSpecialistData(int type,int sort);
    List<String> getTypes();
    List<String> getSorts();
    void showFailedMsg(String msg);
}
