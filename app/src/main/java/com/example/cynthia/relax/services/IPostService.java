package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.PostBean;
import com.example.cynthia.relax.beans.ReplyBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface IPostService {
    @GET("post/getPostList")
    Call<List<PostBean>> getPostList();

    @GET("post/getPostBeanByPostId")
    Call<PostBean> getPostBeanByPostId(@Query("postId") Integer postId);

    @GET("post/getReplyBeanByReplyId")
    Call<ReplyBean> getReplyBeanByReplyId(@Query("replyId") Integer replyId);

    @GET("post/getReplyListById")
    Call<List<ReplyBean>> getReplyListById(@Query("id") Integer id,@Query("isPost") Integer isPost);

    @GET("post/publishPost")
    Call<Integer> publishPost(@Query("userId")Integer userId, @Query("title")String title, @Query("content")String content);

    @GET("post/sendReply")
    Call<Integer> sendReply(@Query("userId")Integer userId, @Query("toId")Integer toId,@Query("isToPost")Integer isToPost, @Query("content")String content);
}
