package com.example.cynthia.relax.activitis.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.historyorder.HistoryOrderView;
import com.example.cynthia.relax.activitis.main.MainActivity;
import com.example.cynthia.relax.activitis.register.RegisterActivity;
import com.example.cynthia.relax.presenters.HistoryOrderPresenter;
import com.example.cynthia.relax.presenters.LoginPresenter;


public class LoginActivity extends AppCompatActivity implements LoginView {
    @Bind(R.id.editLoginPhone)
    EditText editPhone;

    @Bind(R.id.editLoginPwd)
    EditText editPwd;

    @Bind(R.id.loginBtn)
    Button loginBtn;

    @Bind(R.id.redictBtn)
    Button redictBtn;

    @Bind(R.id.loginProgressBar)
    ProgressBar progressBar;

    private SharedPreferences sharedPreferences;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        loginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.loginBtn)
    public void login(View v){
        loginPresenter.login(getUserPhone(),getUserPassword());
    }

    @OnClick(R.id.redictBtn)
    public void skip(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void saveUserIdAndIdentityToSharedPreferences(int userId,int identity){
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("userID", userId);
        editor.putInt("identity",identity);
        editor.commit();
    }

    @Override
    public void showSuccessMsg(int userId) {
        Toast.makeText(LoginActivity.this, "用户" + userId + "登录成功!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedMsg(String s) {
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
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
