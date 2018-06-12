package com.example.cynthia.relax.presenters;

import android.os.Handler;
import com.example.cynthia.relax.activitis.comment.CommentView;
import com.example.cynthia.relax.activitis.login.LoginView;
import com.example.cynthia.relax.services.IOrderService;
import com.example.cynthia.relax.services.IUserService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentPresenter {
    private CommentView commentView;
    private Handler mHandler;

    public CommentPresenter(CommentView commentView) {
        this.commentView = commentView;
        this.mHandler = new Handler();
    }

    public void submitComment(String orderId, float rating, String comment) {
        IOrderService orderService = RetrofitServiceManager.getInstance().create(IOrderService.class);
        Call<Integer> call = orderService.submitComment(orderId, String.valueOf(rating), comment);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    int result = response.body();
                    if (result == 1)
                        commentView.showMsg("评论成功！");
                    else
                        commentView.showMsg("评论失败！");
                    //commentView.commentFinishedIntent();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                commentView.showMsg("评论");
            }
        });
    }
}
