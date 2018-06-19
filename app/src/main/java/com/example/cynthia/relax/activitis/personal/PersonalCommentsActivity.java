package com.example.cynthia.relax.activitis.personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.activitis.specialist_detail.SpecialistDetailCommentAdapter;
import com.example.cynthia.relax.beans.CommentBean;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.presenters.PersonalPresenter;

import java.util.ArrayList;
import java.util.List;

public class PersonalCommentsActivity extends BaseActivity implements PersonalCommentsView{
    @Bind(R.id.commentNum)
    TextView commentNumText;

    @Bind(R.id.rating)
    TextView ratingText;

    @Bind(R.id.personalCommentsList)
    RecyclerView personalCommentsView;

    @Bind(R.id.specialistCommentLayout)
    LinearLayout specialistCommentLayout;

    private LinearLayoutManager linearLayoutManager;
    private SpecialistDetailCommentAdapter specialistDetailCommentAdapter;
    private List<CommentBean> commentBeans;
    private PersonalPresenter personalPresenter;
    private UserBean userBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesonal_comments);
        ButterKnife.bind(this);
        initializeTop(this,true,"我的评价");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userBean = (UserBean) bundle.get("userBean");

        commentBeans = new ArrayList<>();
        personalPresenter = new PersonalPresenter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        specialistDetailCommentAdapter = new SpecialistDetailCommentAdapter(commentBeans,this);
        personalCommentsView.setLayoutManager(linearLayoutManager);
        personalCommentsView.setAdapter(specialistDetailCommentAdapter);

        personalPresenter.getCommentsByUserId(userBean.getUserId(),userBean.getIdentity());

    }


    @Override
    public void bindCommentData(List<CommentBean> userComments) {
        this.commentBeans = userComments;
        if(userBean.getIdentity()==1) {
            specialistCommentLayout.setVisibility(View.VISIBLE);
            commentNumText.setText(userComments.size()+"条");
            double rating = 0;
            for(CommentBean commentBean:userComments){
                rating+=commentBean.getRating();
            }
            rating = rating/userComments.size();
            ratingText.setText(rating+"分");
        }

        specialistDetailCommentAdapter.setCommentBeanList(this.commentBeans);
        specialistDetailCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFailedMsg(String msg) {
        Toast.makeText(PersonalCommentsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
