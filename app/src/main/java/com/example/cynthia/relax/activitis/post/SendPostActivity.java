package com.example.cynthia.relax.activitis.post;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.cynthia.relax.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.activitis.main.MainActivity;
import com.example.cynthia.relax.presenters.PostPresenter;
import com.example.cynthia.relax.services.IPostService;
import com.example.cynthia.relax.services.RetrofitServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendPostActivity extends AppCompatActivity implements SendPostView{

    @Bind(R.id.titleEdit)
    EditText titleEdit;

    @Bind(R.id.contentEdit)
    EditText contentEdit;

    @Bind(R.id.sendBtn)
    Button sendBtn;

    private PostPresenter postPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_post);
        ButterKnife.bind(this);
        postPresenter = new PostPresenter(this);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                Integer userId = sharedPreferences.getInt("userID", 0);
                String title = titleEdit.getText().toString();
                String content = contentEdit.getText().toString();
                if (userId != null && title != null && content != null && title.length() != 0 && content.length() != 0) {
                    postPresenter.sendPost(userId,title,content);
                } else if (userId == null) {
                    Toast.makeText(SendPostActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SendPostActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void redict() {
        Intent intent = new Intent();
        intent.setClass(SendPostActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedMsg(String msg){
        Toast.makeText(SendPostActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }
}
