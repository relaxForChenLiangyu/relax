package com.example.cynthia.relax.activitis.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.MessageBean;

import java.util.ArrayList;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private ArrayList<MessageBean> mMsgList;
    private Context context;
    public static final int TYPE_SENT = 1;
    public static final int TYPE_RECEIVED = 2;

    public MsgAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<MessageBean> msgList){
        this.mMsgList = msgList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mMsgList.get(position).getType();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftTime;
        TextView rightTime;
        TextView leftMsg;
        TextView rightMsg;
        public ViewHolder(View view){
            super(view);
            leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
            leftTime = (TextView)view.findViewById(R.id.left_time);
            rightTime = (TextView)view.findViewById(R.id.right_time);
            leftMsg = (TextView)view.findViewById(R.id.left_msg);
            rightMsg = (TextView)view.findViewById(R.id.right_msg);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if(itemViewType==TYPE_RECEIVED){
            //如果接受消息，则显示左边的消息布局，隐藏右边的消息布局
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftTime.setText(mMsgList.get(position).getCreateTime());
            holder.leftMsg.setText(mMsgList.get(position).getContent());
        }
        else if(itemViewType==TYPE_SENT){
            //如果发送消息，则隐藏左边的消息布局，显示右边的消息布局
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightTime.setText(mMsgList.get(position).getCreateTime());
            holder.rightMsg.setText(mMsgList.get(position).getContent());
        }
    }
    @Override
    public int getItemCount() {
        return mMsgList !=null && mMsgList.size() >0
                ?mMsgList.size()
                :0;
    }



}
