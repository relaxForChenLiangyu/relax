package com.example.cynthia.relax.activitis.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.activitis.login.LoginActivity;
import com.example.cynthia.relax.activitis.main.MainActivity;
import com.example.cynthia.relax.presenters.RegisterPresenter;

public class RegisterActivity extends BaseActivity implements RegisterView{

    @Bind(R.id.editNickName)
    EditText editNickName;

    @Bind(R.id.editRealName)
    EditText editRealName;

    @Bind(R.id.editPhone)
    EditText editPhone;

    @Bind(R.id.editPwd)
    EditText editPwd;

    @Bind(R.id.registerBtn)
    Button registerBtn;

    @Bind(R.id.redictBtn)
    Button redictBtn;

    @Bind(R.id.registerProgressBar)
    ProgressBar progressBar;

    private SharedPreferences sharedPreferences;
    private RegisterPresenter registerPresenter;
    private String nickname;
    private String realname;
    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeTop(this, true, "注册");
        ButterKnife.bind(this);
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        registerPresenter = new RegisterPresenter(this);
        redictBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.registerBtn)
    public void onClick(View v){
        registerPresenter.register(getUserName(), getUserRealName(), getUserPhone(), getUserPassword());

    }


    @Override
    public void saveUserIdToSharedPreferences(int userId){
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("userID", userId);
        editor.commit();
    }

    @Override
    public void showSuccessMsg(int userId) {
        Toast.makeText(RegisterActivity.this, "User " + userId + " Register Success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedMsg(String s) {
        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public String getUserName(){
        return editNickName.getText().toString();
    }

    @Override
    public String getUserRealName(){
        return editRealName.getText().toString();
    }

    @Override
    public String getUserPhone(){
        return editPhone.getText().toString();
    }

    @Override
    public String getUserPassword(){
        return editPwd.getText().toString();
    }

    @Override
    public Context context() {
        return this;
    }
}
