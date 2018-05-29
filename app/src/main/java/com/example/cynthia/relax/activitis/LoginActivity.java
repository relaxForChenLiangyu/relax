package com.example.cynthia.relax.activitis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.User;
import com.example.cynthia.relax.presenters.LoginPresenter;
import com.example.cynthia.relax.views.LoginView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements LoginView {
    @Bind(R.id.editLoginPhone)
    private EditText editPhone;

    @Bind(R.id.editLoginPwd)
    private EditText editPwd;

    @Bind(R.id.loginBtn)
    private Button loginBtn;

    @Bind(R.id.loginProgressBar)
    private ProgressBar progressBar;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        /*loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin(v);
            }
        });*/

        loginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.loginBtn)
    public void onClick(View v){
        clickLogin(v);
    }

    //点击登录
    public void clickLogin(View view) {
        loginPresenter.login();
    }

    @Override
    public void showSuccessMsg(User user) {
        Toast.makeText(LoginActivity.this, "User " + user.getPhone() + " Login Sccess!", Toast.LENGTH_SHORT).show();
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

    public void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://www.baidu.com").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponseData(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!=null) {
                        response.append(line);
                    }
                    showResponseData(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(reader!=null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponseData(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //result.setText(response);
            }
        });
    }
}
