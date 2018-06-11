package com.example.cynthia.relax.presenters;

import com.example.cynthia.relax.activitis.personal.*;
import com.example.cynthia.relax.beans.CommentBean;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.services.ICommentService;
import com.example.cynthia.relax.services.ISpecialistService;
import com.example.cynthia.relax.services.IUserService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

public class PersonalPresenter {
    private PersonalView personalView;
    private EditPasswordView editPasswordView;
    private PersonalCommentsView personalCommentsView;
    private QualifyView qualifyView;

    public PersonalPresenter(PersonalView personalView) {
        this.personalView = personalView;
    }

    public PersonalPresenter(EditPasswordView editPasswordView) {
        this.editPasswordView = editPasswordView;
    }

    public PersonalPresenter(PersonalCommentsView personalCommentsView) {
        this.personalCommentsView = personalCommentsView;
    }

    public PersonalPresenter(QualifyView qualifyView) {
        this.qualifyView = qualifyView;
    }

    public void getCurrentSpecialistInfo(int specialistId) {
        ISpecialistService specialistService = RetrofitServiceManager.getInstance().create(ISpecialistService.class);
        if(specialistId==0)
            personalView.showFailedMsg("您还没有登录_(:3」∠)_");
        else {
            Call<SpecialistBean> call = specialistService.getSpecialistBeanBySpecialistId(specialistId);
            call.enqueue(new Callback<SpecialistBean>() {
                @Override
                public void onResponse(Call<SpecialistBean> call, Response<SpecialistBean> response) {
                    try {
                        SpecialistBean specialistBean = response.body();
                        if (specialistBean != null) {
                            personalView.bindSpecialistData(specialistBean);
                        }
                        else
                            personalView.showFailedMsg("我们没有找到您的个人信息_(:3」∠)_");

                    } catch (Exception e) {
                        personalView.showFailedMsg("网络连接错误");
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SpecialistBean> call, Throwable t) {
                    personalView.showFailedMsg("加载个人信息失败");
                }
            });
        }
    }

    public void editUserPassword(int userId,String curPwd,String newPwd) {
        IUserService userService = RetrofitServiceManager.getInstance().create(IUserService.class);

        Call<Integer> call = userService.editUserPassword(userId,curPwd,newPwd);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    Integer editResult = response.body();
                    if(editResult==0)
                        editPasswordView.showFailedMsg("修改密码成功");
                    if(editResult==1)
                        editPasswordView.showFailedMsg("抱歉，您输入的当前密码不正确");
                } catch (Exception e) {
                    editPasswordView.showFailedMsg("修改密码失败");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                editPasswordView.showFailedMsg("网络连接错误");
            }
        });
    }

    public void getCommentsByUserId(int userId,int identity) {
        ICommentService commentService = RetrofitServiceManager.getInstance().create(ICommentService.class);
        Call<List<CommentBean>> call = commentService.getCommentsByUserIdByIdentity(userId,identity);
        call.enqueue(new Callback<List<CommentBean>>() {
            @Override
            public void onResponse(Call<List<CommentBean>> call, Response<List<CommentBean>> response) {
                try {
                    List<CommentBean> commentBeans = response.body();
                    if (commentBeans != null&&commentBeans.size()!=0) {
                        personalCommentsView.bindCommentData(commentBeans);
                    }
                    else
                        personalCommentsView.showFailedMsg("您还没有评价_(:3」∠)_");
                } catch (Exception e) {
                    personalCommentsView.showFailedMsg("加载评价失败");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<CommentBean>> call, Throwable t) {
                personalCommentsView.showFailedMsg("网络连接错误");
            }
        });
    }

    public void submitQualification(Map<String,Object> resume) {
        ISpecialistService specialistService = RetrofitServiceManager.getInstance().create(ISpecialistService.class);
        Call<Integer> call = specialistService.submitQualification(resume);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    int result = response.body();
                    if (result==1) {
                        qualifyView.showSuccessMsg("认证专家成功");
                    }
                    else
                        qualifyView.showFailedMsg("您已经认证过了");
                } catch (Exception e) {
                    qualifyView.showFailedMsg("认证资料提交失败");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                qualifyView.showFailedMsg("网络连接错误");
            }
        });
    }
}
