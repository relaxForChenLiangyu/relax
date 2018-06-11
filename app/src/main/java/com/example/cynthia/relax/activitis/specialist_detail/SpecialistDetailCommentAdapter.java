package com.example.cynthia.relax.activitis.specialist_detail;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.CommentBean;

import java.util.Date;
import java.util.List;


public class SpecialistDetailCommentAdapter extends RecyclerView.Adapter<SpecialistDetailCommentAdapter.ViewHolder>{
    private List<CommentBean> commentBeanList;
    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView rating;
        ImageView portrait;
        TextView commentContent;
        TextView commentTime;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            name = (TextView)itemView.findViewById(R.id.sdUserName);
            rating = (TextView)itemView.findViewById(R.id.sdUserRating);
            portrait = (ImageView)itemView.findViewById(R.id.sdUserPortrait);
            commentContent = (TextView)itemView.findViewById(R.id.sdUserComment);
            commentTime = (TextView)itemView.findViewById(R.id.sdUserCommentTime);
        }
    }


    public SpecialistDetailCommentAdapter(List<CommentBean> commentBeanList,Activity activity){
        this.commentBeanList = commentBeanList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentBean commentBean = commentBeanList.get(position);
        holder.view.setTag(position);
        holder.name.setText(commentBean.getCommenterName());
        holder.rating.setText(commentBean.getRating().toString());
        holder.commentTime.setText(commentBean.getCommentTime().toString());
        holder.commentContent.setText(commentBean.getContent());
        //holder.portrait.setImageResource(commentBean.getPortrait());
    }

    @Override
    public int getItemCount() {
        return commentBeanList.size();
    }

    public void setCommentBeanList(List<CommentBean> commentBeanList) {
        this.commentBeanList = commentBeanList;
    }
}
