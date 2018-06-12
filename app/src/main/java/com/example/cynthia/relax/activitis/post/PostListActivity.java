package com.example.cynthia.relax.activitis.post;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.beans.PostBean;
import com.example.cynthia.relax.presenters.PostPresenter;

import java.util.ArrayList;
import java.util.List;

public class PostListActivity extends BaseActivity implements PostView{
    @Bind(R.id.questionBtn)
    Button questionBtn;

    @Bind(R.id.refresh)
    ImageButton refresh;

    List<PostBean> postBeans;

    private RecyclerView postRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private PostAdapter postAdapter;
    private PostPresenter postPresenter;

    @OnClick(R.id.questionBtn)
    public void question(View view){
        Intent intent = new Intent();
        intent.setClass(PostListActivity.this, SendPostActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.refresh)
    public void refresh(View view){
        finish();
        Intent intent = new Intent();
        intent.setClass(PostListActivity.this, PostListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        initializeTop(this, true, "树洞");
        ButterKnife.bind(this);
        postBeans = new ArrayList<>();
        postPresenter = new PostPresenter(this);

        postRecyclerView = (RecyclerView)findViewById(R.id.postList);

        gridLayoutManager = new GridLayoutManager(this,1);
        postAdapter = new PostAdapter(postBeans, this);

        postRecyclerView.setLayoutManager(gridLayoutManager);
        postRecyclerView.setAdapter(postAdapter);

        getPostListData();
    }


    public class postThread implements Runnable {
        @Override
        public void run() {
            getPostListData();
        }
    }

    @Override
    public void getPostListData(){
        postPresenter.getPostList();
    }

    @Override
    public void setPostListData(List<PostBean> postBeans){
        this.postBeans = postBeans;
        postAdapter.setPostBeanList(this.postBeans);
        postAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFailedMsg(String msg){
        Toast.makeText(PostListActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }


}
