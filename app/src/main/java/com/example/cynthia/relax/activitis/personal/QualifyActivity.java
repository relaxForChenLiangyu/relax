package com.example.cynthia.relax.activitis.personal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.main.MainActivity;
import com.example.cynthia.relax.beans.SpecialistBean;
import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.presenters.PersonalPresenter;

import java.util.HashMap;
import java.util.Map;

public class QualifyActivity extends AppCompatActivity implements QualifyView{
    @Bind(R.id.qualificationName)
    TextView nameText;

    @Bind(R.id.qualificationContentTitle)
    TextView qualificationText;

    @Bind(R.id.qualificationEmployeeLength)
    TextView employeeLengthText;

    @Bind(R.id.qualificationSalary)
    TextView salaryText;

    private String name;

    private UserBean userBean;
    private PersonalPresenter personalPresenter;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualify);
        ButterKnife.bind(this);

        personalPresenter = new PersonalPresenter(this);
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userBean = (UserBean)bundle.getSerializable("userBean");
        nameText.setText(userBean.getRealName());


    }

    @OnClick(R.id.submitQualification)
    public void submit(View v){
        String name = nameText.getText().toString();
        String qualification = qualificationText.getText().toString();
        String employeeLength = employeeLengthText.getText().toString();
        String salary = salaryText.getText().toString();
        if(!name.equals("")&&!qualification.equals("")&&!employeeLength.equals("")&&!salary.equals("")) {
            Map<String,Object> resume = new HashMap<>();
            resume.put("userId",userBean.getUserId());
            resume.put("name",name);
            resume.put("qualification",qualification);
            resume.put("employeeLength",employeeLength);
            resume.put("introduction",salary);
            personalPresenter.submitQualification(resume);
        }
        else
            showFailedMsg("认证信息不能为空");
    }

    @Override
    public void showSuccessMsg(String msg) {
        Toast.makeText(QualifyActivity.this, msg, Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(QualifyActivity.this,MainActivity.class);
    }

    @Override
    public void showFailedMsg(String msg) {
        Toast.makeText(QualifyActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveIdentityToSharedPreferences(int identity) {
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.remove("identity");
        editor.putInt("identity",1);
        editor.commit();
    }
}
