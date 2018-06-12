package com.example.cynthia.relax.activitis.specialist_detail;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.activitis.main.MainActivity;
import com.example.cynthia.relax.activitis.order.OrderActivity;
import com.example.cynthia.relax.beans.CommentBean;
import com.example.cynthia.relax.beans.PreOrderStatusBean;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.beans.TypeBean;
import com.example.cynthia.relax.presenters.SpecialistDetailPresenter;

import java.util.*;

public class SpecialistDetailActivity extends BaseActivity implements SpecialistDetailView{
    @Bind(R.id.preOrderTable)
    TableLayout preOrderTable;

    @Bind(R.id.specialistInquiryType)
    TextView inquiryType;

    @Bind(R.id.specialistName)
    TextView specialistName;

    @Bind(R.id.specialistEmployLength)
    TextView employLength;

    @Bind(R.id.specialistQualification)
    TextView qualification;

    @Bind(R.id.specialistRating)
    TextView rating;

    @Bind(R.id.specialistUserCommentRecyclerView)
    RecyclerView userCommentRecyclerView;

    @Bind(R.id.noComments)
    TextView noComments;


    private SpecialistDetailPresenter specialistDetailPresenter;
    private LinearLayoutManager linearLayoutManager;
    private SpecialistDetailCommentAdapter specialistDetailCommentAdapter;

    private List<String> timeSlot;
    private SpecialistBean specialistBean;
    private List<PreOrderStatusBean> preOrderStatusBeanData;
    private String date = "";
    private int row = 0;
    private int col = 0;
    private String nextWeekStatus="";
    private List<CommentBean> commentBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_detail);
        initializeTop(this, true, "专家信息");
        ButterKnife.bind(this);
        bindData();
        specialistDetailPresenter = new SpecialistDetailPresenter(this);
        preOrderStatusBeanData = new ArrayList<>();
        commentBeans = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        specialistDetailCommentAdapter = new SpecialistDetailCommentAdapter(commentBeans,this);
        userCommentRecyclerView.setLayoutManager(linearLayoutManager);
        userCommentRecyclerView.setAdapter(specialistDetailCommentAdapter);

        new Thread(new SpecialistDetailThread()).start();



    }

    public class SpecialistDetailThread implements Runnable {
        @Override
        public void run() {
            try{
                if(specialistBean!=null) {
                    getSpecialistBeanBySpecialistId();
                }
            }catch (Exception e){
                showFailedMsg("网络连接错误");
            }

        }
    }

    public void bindData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.specialistBean = (SpecialistBean) bundle.get("specialistBean");
        String type="";
        int count = 0;
        for(TypeBean typeBean:specialistBean.getTypeBeanList()){
            if(count==0)
                type = typeBean.getTitle();
            else
                type=type+","+typeBean.getTitle();
            count++;
        }
        specialistName.setText(specialistBean.getRealName());
        inquiryType.setText(type);
        employLength.setText(specialistBean.getEmployLength()+"年");
        qualification.setText(specialistBean.getQualification());
        rating.setText(specialistBean.getRating().toString()+"分");
    }

    @Override
    public void initTable(final List<PreOrderStatusBean> data){
        final List<String> timeSlotList = getTimeSlot();
        preOrderTable.setStretchAllColumns(true);
        Date curDate = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(curDate);
        int curDay = curDate.getDay();
        for(row = 0;row < 8;row++){
            TableRow tableRow =  new TableRow(this);
            tableRow.setMinimumHeight(80);
            if(row==0){
                //Table Title
                tableRow.setBackgroundColor(Color.rgb(255,255,213));
                for(col = 0;col<8;col++){
                    TextView textView = new TextView(this);
                    textView.setPadding(5,5,5,5);
                    if(col==0) {
                        textView.setText("日期");
                        textView.setBackgroundColor(Color.rgb(255,255,213));
                    }
                    else{
                        Date dt1=rightNow.getTime();
                        date = (dt1.getMonth()+1)+"."+dt1.getDate();
                        textView.setText(date);
                        rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
                    }
                    tableRow.addView(textView);
                }
            }
            else{
                //Table Body
                tableRow.setBackgroundColor(Color.WHITE);
                for(col = 0;col<8;col++){
                    final TextView textView = new TextView(this);
                    textView.setPadding(5,5,5,5);
                    if(col==0) {
                        textView.setText(timeSlotList.get(row));
                        textView.setTextColor(Color.rgb(119,133,255));
                    }
                    else {
                        PreOrderStatusBean statusBean = data.get((curDay-2+col)+7*(row-1));
                        if(statusBean.getIsFree().equals(0)) {
                            textView.setText("休息");
                            textView.setBackgroundColor(Color.rgb(234,234,234));
                            nextWeekStatus+="2";
                        }
                        else{
                            if(statusBean.getIsOrdered().equals(1)) {
                                textView.setText("忙碌");
                                textView.setBackgroundColor(Color.rgb(234,234,234));
                                nextWeekStatus+="1";
                            }
                            else {
                                textView.setText("空闲");
                                nextWeekStatus+="0";
                                final int pos  = (row+1)*10+(col+1);//在表格的第几行第几列
                                textView.setId(pos);
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //textView.setBackgroundColor(Color.rgb(218, 255, 181));
                                        specialistBean.setPreOrderStatusBeanList(data);
                                        int pos_r = pos % 10;
                                        int pos_c = pos / 10;
                                        Date cur = new Date();
                                        Calendar curC = Calendar.getInstance();
                                        curC.setTime(cur);
                                        curC.add(Calendar.DAY_OF_YEAR,pos_c-2);
                                        Date dt=curC.getTime();
                                        String selected_date = (dt.getMonth()+1)+"."+dt.getDate();
                                        String[] nextWeekStatusArr = nextWeekStatus.split("-");
                                        String selectedDayStatus = "";
                                        for(int i=0;i<nextWeekStatusArr.length;i++){
                                            selectedDayStatus = selectedDayStatus + nextWeekStatusArr[i].substring(pos_c - 1,pos_c);
                                        }
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("specialistBean",specialistBean);
                                        bundle.putString("selectedDate",selected_date);
                                        bundle.putString("selectedTimeSlot",timeSlotList.get(pos_r-1));
                                        bundle.putString("selectedDayStatus",selectedDayStatus);
                                        intent.putExtras(bundle);
                                        intent.setClass(SpecialistDetailActivity.this, OrderActivity.class);//待修改
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    }
                    tableRow.addView(textView);
                }
                nextWeekStatus+="-";
            }
            preOrderTable.addView(tableRow);

        }

    }

    @Override
    public void setNoCommentText(String msg) {
        noComments.setText(msg);
        noComments.setVisibility(View.VISIBLE);
    }

    @Override
    public void getSpecialistBeanBySpecialistId(){
        specialistDetailPresenter.getSpecialistBeanBySpecialistId(specialistBean.getSpecialistId());
    }

    public List<String> getTimeSlot(){
        timeSlot = new ArrayList<>();
        timeSlot.add("9:00~10:00");
        timeSlot.add("10:00~11:00");
        timeSlot.add("13:00~14:00");
        timeSlot.add("14:00~15:00");
        timeSlot.add("15:00~16:00");
        timeSlot.add("16:00~17:00");
        timeSlot.add("19:00~20:00");
        timeSlot.add("20:00~21:00");
        return timeSlot;
    }

    @Override
    public void showFailedMsg(String msg) {
        Toast.makeText(SpecialistDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserComments(List<CommentBean> userComments) {
        this.commentBeans = userComments;
        specialistDetailCommentAdapter.setCommentBeanList(this.commentBeans);
        specialistDetailCommentAdapter.notifyDataSetChanged();
    }

}
