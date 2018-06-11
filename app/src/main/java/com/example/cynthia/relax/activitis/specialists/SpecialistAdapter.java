package com.example.cynthia.relax.activitis.specialists;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.specialist_detail.SpecialistDetailActivity;
import com.example.cynthia.relax.beans.SpecialistBean;

import java.io.Serializable;
import java.util.List;

public class SpecialistAdapter extends RecyclerView.Adapter<SpecialistAdapter.ViewHolder> implements View.OnClickListener{
    private List<SpecialistBean> specialistBeanList;
    private Activity activity;


    @Override
    public void onClick(View v){
        skip(specialistBeanList.get((int)v.getTag()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView rating;
        ImageView portrait;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            name = (TextView)itemView.findViewById(R.id.specialistItemName);
            rating = (TextView)itemView.findViewById(R.id.specialistItemRating);
            portrait = (ImageView)itemView.findViewById(R.id.specialistItemPortrait);
        }
    }


    public SpecialistAdapter(List<SpecialistBean> specialistBeanList,Activity activity){
        this.specialistBeanList = specialistBeanList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_specialist,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SpecialistAdapter.ViewHolder holder, int position) {
        SpecialistBean specialistBean = specialistBeanList.get(position);
        holder.view.setTag(position);
        holder.name.setText(specialistBean.getRealName());
        holder.rating.setText(specialistBean.getRating().toString());
        //holder.portrait.setImageResource(specialistBean.getPortrait());
    }

    @Override
    public int getItemCount() {
        return specialistBeanList.size();
    }

    public void setSpecialistBeanList(List<SpecialistBean> specialistBeanList) {
        this.specialistBeanList = specialistBeanList;
    }

    public void skip(SpecialistBean specialistBean) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("specialistBean", specialistBean);
        intent.putExtras(bundle);
        intent.setClass(getActivity(), SpecialistDetailActivity.class);
        startActivity(intent);
    }

    public Activity getActivity() {
        return activity;
    }

    public void startActivity(Intent intent) {
        this.activity.startActivity(intent);
    }
}
