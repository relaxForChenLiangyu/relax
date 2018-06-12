package com.example.cynthia.relax.activitis.personal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.presenters.PersonalPresenter;

public class EditPasswordActivity extends BaseActivity implements EditPasswordView {
    @Bind(R.id.curPwd)
    EditText curPwdText;

    @Bind(R.id.newPwd)
    EditText newPwdText;

    @Bind(R.id.confirmPwd)
    EditText confirmPwdText;

    private String curPwd;
    private String newPwd;
    private String confirmPwd;
    private PersonalPresenter personalPresenter;
    private SharedPreferences sharedPreferences;
    private int curUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        initializeTop(this, true, "修改密码");
        ButterKnife.bind(this);
        personalPresenter = new PersonalPresenter(this);
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        curUserId = sharedPreferences.getInt("userID", 0);
    }

    @OnClick(R.id.submitPwd)
    public void editPwd(View v){
        curPwd = curPwdText.getText().toString();
        newPwd = newPwdText.getText().toString();
        confirmPwd = confirmPwdText.getText().toString();
        if(curPwd.length()==0||newPwd.length()==0||confirmPwd.length()==0)
            showFailedMsg("密码不能为空");
        else if(!newPwd.equals(confirmPwd))
            showFailedMsg("两遍密码不一致");
        else
            personalPresenter.editUserPassword(curUserId,curPwd,newPwd);

    }

    @Override
    public void showFailedMsg(String s) {
        Toast.makeText(EditPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
