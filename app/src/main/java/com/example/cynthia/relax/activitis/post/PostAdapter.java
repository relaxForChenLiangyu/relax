package com.example.cynthia.relax.activitis.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.PostBean;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> implements View.OnClickListener{
    private List<PostBean> postBeanList;
    private Activity activity;

    @Override
    public void onClick(View v){
        skip(postBeanList.get((int)v.getTag()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView title;
        TextView content;
        TextView userName;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            title = (TextView)itemView.findViewById(R.id.title);
            content = (TextView)itemView.findViewById(R.id.content);
            userName = (TextView)itemView.findViewById(R.id.user);
            time = (TextView)itemView.findViewById(R.id.time);
        }
    }
    public PostAdapter(List<PostBean> postBeanList,Activity activity){
        this.postBeanList = postBeanList;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, int position) {
        holder.view.setTag(position);
        PostBean postBean = postBeanList.get(position);
        holder.title.setText(postBean.getTitle());
        if(postBean.getContent().length()>140){
            holder.content.setText(postBean.getContent().substring(0,140)+"...");
        }else {
            holder.content.setText(postBean.getContent());
        }
        holder.userName.setText(postBean.getUserName());
        holder.time.setText(postBean.getTime());
    }

    public void skip(PostBean postBean) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("postBean", postBean);
        intent.putExtras(bundle);
        intent.setClass(getActivity(), PostDetailActivity.class);
        startActivity(intent);
    }

    public Activity getActivity() {
        return activity;
    }

    public void startActivity(Intent intent) {
        this.activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return postBeanList.size();
    }

    public void setPostBeanList(List<PostBean> postBeanList) {
        this.postBeanList = postBeanList;
    }
}