package com.example.cynthia.relax.presenters;

import android.content.Intent;
import com.example.cynthia.relax.activitis.main.MainActivity;
import com.example.cynthia.relax.activitis.post.PostView;
import com.example.cynthia.relax.activitis.post.SendPostActivity;
import com.example.cynthia.relax.activitis.post.SendPostView;
import com.example.cynthia.relax.beans.PostBean;
import com.example.cynthia.relax.services.IPostService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PostPresenter {
    private PostView postView;

    private SendPostView sendPostView;

    public PostPresenter(PostView postView) {
        this.postView = postView;
    }

    public PostPresenter(SendPostView sendPostView) {
        this.sendPostView = sendPostView;
    }

    public void sendPost(Integer userId,String title,String content) {
        IPostService postService = RetrofitServiceManager.getInstance().create(IPostService.class);
        Call<Integer> call = postService.publishPost(userId, title, content);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    Integer postId = response.body();
                    if (postId != null) {
                        sendPostView.showFailedMsg("发布成功");
                        sendPostView.redict();
                    } else {
                        sendPostView.showFailedMsg("发布失败");
                    }
                } catch (Exception e) {
                    sendPostView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                sendPostView.showFailedMsg("发布失败");
            }
        });
    }

    public void getPostList() {
        IPostService postService = RetrofitServiceManager.getInstance().create(IPostService.class);
        Call<List<PostBean>> call = postService.getPostList();
        call.enqueue(new Callback<List<PostBean>>() {
            @Override
            public void onResponse(Call<List<PostBean>> call, Response<List<PostBean>> response){
                try {
                    List<PostBean> postBeanList = response.body();
                    if(postBeanList!=null && postBeanList.size()!=0)
                        postView.setPostListData(postBeanList);
                    else {
                        postView.setPostListData(postBeanList);
                        postView.showFailedMsg("还没有问题");
                    }
                } catch (Exception e) {
                    postView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<PostBean>> call, Throwable t) {
                postView.showFailedMsg("加载问题列表失败");
            }
        });
    }

}
