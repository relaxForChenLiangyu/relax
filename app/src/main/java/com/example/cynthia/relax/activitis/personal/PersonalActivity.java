package com.example.cynthia.relax.activitis.personal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.activitis.main.MainActivity;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.presenters.PersonalPresenter;

public class PersonalActivity extends BaseActivity implements PersonalView{

    @Bind(R.id.personalEditPwdBtn)
    Button editPwdBtn;

    @Bind(R.id.personalName)
    TextView nickName;

    @Bind(R.id.personalPhone)
    TextView phone;

    @Bind(R.id.personalQualification)
    TextView qualification;

    @Bind(R.id.personalRealName)
    TextView realName;

    @Bind(R.id.personalRelaxDegree)
    TextView relaxDegree;

    @Bind(R.id.personalRemainder)
    TextView remainder;

    @Bind(R.id.personalSpInfo)
    LinearLayout personalSpLayout;

    @Bind(R.id.personalSpQualify)
    TextView personalSpQualify;

    @Bind(R.id.personalSpLength)
    TextView personalSpLength;

    @Bind(R.id.personalSpIntro)
    TextView personalSpIntro;

    @Bind(R.id.editInfoBtn)
    TextView editInfoBtn;

    private UserBean userBean;
    private PersonalPresenter personalPresenter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initializeTop(this, true, "个人中心");
        ButterKnife.bind(this);
        personalPresenter = new PersonalPresenter(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userBean = (UserBean)bundle.get("userBean");
        if(userBean.getIdentity()==1) {
            personalPresenter.getCurrentSpecialistInfo(userBean.getUserId());
        }
        bindData();
    }

    public void bindSpecialistData(SpecialistBean specialistBean){
        personalSpLayout.setVisibility(View.VISIBLE);
        personalSpQualify.setText(specialistBean.getQualification());
        personalSpIntro.setText(specialistBean.getIntroduction());
        personalSpLength.setText(specialistBean.getEmployLength()+"年");
    }

    public void bindData(){
        realName.setText(userBean.getRealName());
        nickName.setText(userBean.getNickname());
        phone.setText(userBean.getPhone());
        if(userBean.getIdentity()==0)
            qualification.setText("未认证");
        else
            qualification.setText("已认证");
        relaxDegree.setText(userBean.getRelaxDegree().toString());
        remainder.setText(userBean.getRemainder()+"元");
    }

    @Override
    public void showFailedMsg(String s) {
        Toast.makeText(PersonalActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.personalEditPwdBtn)
    public void skip(View v){
        Intent intent = new Intent();
        intent.setClass(PersonalActivity.this,EditPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.editInfoBtn)
    public void skipToEdit(View v){
        Intent intent = new Intent();
        intent.setClass(PersonalActivity.this,EditPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.quitBtn)
    public void quit(View v){
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
        Intent intent = new Intent();
        intent.setClass(PersonalActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}
