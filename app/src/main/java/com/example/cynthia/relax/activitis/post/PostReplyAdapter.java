package com.example.cynthia.relax.activitis.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.ReplyBean;

import java.util.List;


public class PostReplyAdapter extends RecyclerView.Adapter<PostReplyAdapter.ViewHolder> {
    private List<ReplyBean> replys;
    private Activity activity;
    private MoreReplyView moreReplyView;
    private PostDetailView postDetailView;
    private Integer which;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView userName;
        TextView replyContent;
        TextView time;
        Button moreBtn;
        ImageButton replyBtn;
        LinearLayout replyLayout;
        EditText editComment;
        Button commentBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            replyContent = (TextView)itemView.findViewById(R.id.replyContent);
            userName = (TextView)itemView.findViewById(R.id.userName);
            time = (TextView)itemView.findViewById(R.id.time);
            moreBtn = (Button)itemView.findViewById(R.id.moreBtn);
            replyBtn = (ImageButton)itemView.findViewById(R.id.replyBtn);
            replyLayout = (LinearLayout)itemView.findViewById(R.id.replyLayout);
            editComment = (EditText)itemView.findViewById(R.id.editComment);
            commentBtn = (Button)itemView.findViewById(R.id.commentBtn);
            replyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(replyLayout.getVisibility()==View.GONE) {
                        replyLayout.setVisibility(View.VISIBLE);
                    }else {
                        replyLayout.setVisibility(View.GONE);
                    }
                }
            });

        }
    }
    public PostReplyAdapter(List<ReplyBean> replys,Activity activity,MoreReplyView moreReplyView){
        this.replys = replys;
        this.activity = activity;
        this.moreReplyView = moreReplyView;
        this.which = 0;
    }

    public PostReplyAdapter(List<ReplyBean> replys,Activity activity,PostDetailView postDetailView){
        this.replys = replys;
        this.activity = activity;
        this.postDetailView  = postDetailView;
        this.which = 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reply_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PostReplyAdapter.ViewHolder holder, int position) {
        holder.view.setTag(position);
        final ReplyBean replyBean = replys.get(position);
        holder.userName.setText(replyBean.getUserName());
        holder.replyContent.setText(replyBean.getContent());
        holder.time.setText(replyBean.getReplyTime());
        Integer replyNum = replyBean.getReplyNum();
        if(replyNum!=null && replyNum>0) {
            holder.moreBtn.setText("共"+replyNum.toString()+"条回复>");
            holder.moreBtn.setVisibility(View.VISIBLE);
            holder.moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("replyBean", replyBean);
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), MoreReplyActivity.class);
                    startActivity(intent);
                }
            });
        }
        holder.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer result = -1;
                String content = holder.editComment.getText().toString();
                Integer toId = replys.get((int)holder.view.getTag()).getPostReplyId();
                if(which==0){
                    result = moreReplyView.sendReply(toId,content);
                    if(result==0) {
                        holder.editComment.setText("");
                        holder.replyLayout.setVisibility(View.GONE);
                    }
                }else {
                    result = postDetailView.sendReply(toId,content);
                    if(result==0) {
                        holder.editComment.setText("");
                        holder.replyLayout.setVisibility(View.GONE);
                    }
                }


            }
        });

    }

    public Activity getActivity() {
        return activity;
    }

    public void startActivity(Intent intent) {
        this.activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return replys.size();
    }

    public void setReplyList(List<ReplyBean> replys) {
        this.replys = replys;
    }

}
