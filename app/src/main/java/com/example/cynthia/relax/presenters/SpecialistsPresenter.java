package com.example.cynthia.relax.presenters;

import com.example.cynthia.relax.activitis.specialists.SpecialistView;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.services.ISpecialistService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class SpecialistsPresenter {
    private SpecialistView specialistView;

    public SpecialistsPresenter(SpecialistView specialistView) {
        this.specialistView = specialistView;
    }

    public void getSortedSpecialistByType(int type,int sort) {
        ISpecialistService specialistService = RetrofitServiceManager.getInstance().create(ISpecialistService.class);
        Call<List<SpecialistBean>> call = specialistService.getSpecialists(type, sort);
        call.enqueue(new Callback<List<SpecialistBean>>() {
            @Override
            public void onResponse(Call<List<SpecialistBean>> call, Response<List<SpecialistBean>> response){
                try {
                    List<SpecialistBean> specialistBeanList = response.body();
                    if(specialistBeanList!=null&&specialistBeanList.size()!=0)
                        specialistView.setSpecialistData(specialistBeanList);
                    else {
                        specialistView.setSpecialistData(specialistBeanList);
                        specialistView.showFailedMsg("还没有专家入驻_(:3」∠)_");
                    }

                } catch (Exception e) {
                    specialistView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<SpecialistBean>> call, Throwable t) {
                specialistView.showFailedMsg("加载专家列表失败");
            }
        });
    }
}
