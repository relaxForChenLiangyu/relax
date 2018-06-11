package com.example.cynthia.relax.presenters;

import com.example.cynthia.relax.activitis.specialist_detail.SpecialistDetailView;
import com.example.cynthia.relax.beans.CommentBean;
import com.example.cynthia.relax.beans.PreOrderStatusBean;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.services.ISpecialistService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class SpecialistDetailPresenter {
    private SpecialistDetailView specialistDetailView;

    public SpecialistDetailPresenter(SpecialistDetailView specialistDetailView) {
        this.specialistDetailView = specialistDetailView;
    }

    public void getSpecialistBeanBySpecialistId(int specialistId) {
        ISpecialistService specialistService = RetrofitServiceManager.getInstance().create(ISpecialistService.class);
        Call<SpecialistBean> call = specialistService.getSpecialistBeanBySpecialistId(specialistId);
        call.enqueue(new Callback<SpecialistBean>() {
            @Override
            public void onResponse(Call<SpecialistBean> call, Response<SpecialistBean> response){
                try {
                    List<PreOrderStatusBean> preOrderStatusBeanList = response.body().getPreOrderStatusBeanList();
                    List<CommentBean> userCommentBeanList = response.body().getCommentBeanList();
                    if(preOrderStatusBeanList!=null&&preOrderStatusBeanList.size()!=0)
                        specialistDetailView.initTable(preOrderStatusBeanList);
                    else
                        specialistDetailView.showFailedMsg("该专家还没有设置预约时间，看看别的专家吧_(:3」∠)_");

                    if(userCommentBeanList!=null&&userCommentBeanList.size()!=0)
                        specialistDetailView.setUserComments(userCommentBeanList);
                    else
                        specialistDetailView.setNoCommentText("该专家暂时还没有评论");

                } catch (Exception e) {
                    specialistDetailView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<SpecialistBean> call, Throwable t) {
                specialistDetailView.showFailedMsg("加载专家信息失败");
            }
        });
    }
}
