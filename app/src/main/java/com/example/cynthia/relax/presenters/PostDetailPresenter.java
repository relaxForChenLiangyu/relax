package com.example.cynthia.relax.presenters;

import com.example.cynthia.relax.activitis.post.MoreReplyView;
import com.example.cynthia.relax.activitis.post.PostDetailView;
import com.example.cynthia.relax.beans.PostBean;
import com.example.cynthia.relax.beans.ReplyBean;
import com.example.cynthia.relax.services.IPostService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PostDetailPresenter {
    private PostDetailView postDetailView;

    private Integer result = -1;

    private MoreReplyView moreReplyView;

    public PostDetailPresenter(MoreReplyView moreReplyView) {
        this.moreReplyView = moreReplyView;
    }

    public PostDetailPresenter(PostDetailView postDetailView) {
        this.postDetailView = postDetailView;
    }

    public Integer sendReply(Integer userId, Integer toId, final Integer isToPost, String content,final Integer from) {
        result = -1;
        IPostService postService = RetrofitServiceManager.getInstance().create(IPostService.class);
        Call<Integer> call = postService.sendReply(userId, toId, isToPost, content);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    Integer replyId = response.body();
                    if(replyId!=null&&replyId>0){
                        if(from==1) {
                            postDetailView.getReplyListByPostId();
                            postDetailView.showFailedMsg("回复成功");
                            postDetailView.hideEdit();
                        }else {
                            moreReplyView.getReplyListByReplyId();
                            moreReplyView.showFailedMsg("回复成功");
                            moreReplyView.hideEdit();
                        }
                        result = 0;
                    }else {
                        if(from==1) {
                            postDetailView.showFailedMsg("回复失败");
                        }else {
                            moreReplyView.showFailedMsg("回复失败");
                        }
                    }

                }catch (Exception e) {
                    if(from==1)
                        postDetailView.showFailedMsg("网络连接错误");
                    else
                        moreReplyView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                if(from==1)
                    postDetailView.showFailedMsg("加载失败");
                else
                    moreReplyView.showFailedMsg("加载失败");
            }
        });
        return result;
    }

    public void getPostBeanByPostId(int postId) {
        IPostService postService = RetrofitServiceManager.getInstance().create(IPostService.class);
        Call<PostBean> call = postService.getPostBeanByPostId(postId);
        call.enqueue(new Callback<PostBean>() {
            @Override
            public void onResponse(Call<PostBean> call, Response<PostBean> response){
                try {
                    postDetailView.setPostBean(response.body());

                } catch (Exception e) {
                    postDetailView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<PostBean> call, Throwable t) {
                postDetailView.showFailedMsg("树洞加载失败");
            }
        });
    }

    public void getReplyBeanByReplyId(int replyId) {
        IPostService postService = RetrofitServiceManager.getInstance().create(IPostService.class);
        Call<ReplyBean> call = postService.getReplyBeanByReplyId(replyId);
        call.enqueue(new Callback<ReplyBean>() {
            @Override
            public void onResponse(Call<ReplyBean> call, Response<ReplyBean> response){
                try {
                    moreReplyView.setReplyBean(response.body());

                } catch (Exception e) {
                    moreReplyView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ReplyBean> call, Throwable t) {
                moreReplyView.showFailedMsg("更多回复加载失败");
            }
        });
    }

    public void getReplyListByPostId(Integer id){
        IPostService postService = RetrofitServiceManager.getInstance().create(IPostService.class);
        Call<List<ReplyBean>> call = postService.getReplyListById(id,0);
        call.enqueue(new Callback<List<ReplyBean>>() {
            @Override
            public void onResponse(Call<List<ReplyBean>> call, Response<List<ReplyBean>> response){
                try {
                    List<ReplyBean> replys = response.body();
                    if(replys.size()!=0 && replys!=null)
                        postDetailView.setReplyList(replys);
                    else
                        postDetailView.setNoReplyText("暂时没有回复");

                } catch (Exception e) {
                    postDetailView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<ReplyBean>> call, Throwable t) {
                postDetailView.showFailedMsg("回复加载失败");
            }
        });
    }

    public void getReplyListByReplyId(Integer id){
        IPostService postService = RetrofitServiceManager.getInstance().create(IPostService.class);
        Call<List<ReplyBean>> call = postService.getReplyListById(id,1);
        call.enqueue(new Callback<List<ReplyBean>>() {
            @Override
            public void onResponse(Call<List<ReplyBean>> call, Response<List<ReplyBean>> response){
                try {
                    List<ReplyBean> replys = response.body();
                    if(replys.size()!=0 && replys!=null)
                        moreReplyView.setReplyList(replys);

                } catch (Exception e) {
                    moreReplyView.showFailedMsg("网络连接错误");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<ReplyBean>> call, Throwable t) {
                moreReplyView.showFailedMsg("回复加载失败");
            }
        });
    }

}
