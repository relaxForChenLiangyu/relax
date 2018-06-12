package com.example.cynthia.relax.services;

import com.example.cynthia.relax.beans.CommentBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ICommentService {
    @GET("comment/getCommentsByUserIdByIdentity")
    Call<List<CommentBean>> getCommentsByUserIdByIdentity(@Query("userId") Integer userId,@Query("identity") Integer identity);
}
