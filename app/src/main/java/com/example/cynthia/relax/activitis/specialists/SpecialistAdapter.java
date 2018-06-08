package com.example.cynthia.relax.activitis.specialists;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.SpecialistBean;

import java.util.List;

public class SpecialistAdapter extends RecyclerView.Adapter<SpecialistAdapter.ViewHolder>{
    private List<SpecialistBean> specialistBeanList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView rating;
        ImageView portrait;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.specialistItemName);
            rating = (TextView)itemView.findViewById(R.id.specialistItemRating);
            portrait = (ImageView)itemView.findViewById(R.id.specialistItemPortrait);
        }
    }
    public SpecialistAdapter(List<SpecialistBean> specialistBeanList){
        this.specialistBeanList = specialistBeanList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_specialist,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SpecialistAdapter.ViewHolder holder, int position) {
        SpecialistBean specialistBean = specialistBeanList.get(position);
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
}
