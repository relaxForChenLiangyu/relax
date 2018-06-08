package com.example.cynthia.relax.activitis.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.login.LoginActivity;
import com.example.cynthia.relax.activitis.register.RegisterActivity;
import com.example.cynthia.relax.activitis.specialists.SpecialistsActivity;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.preOrder)
    ImageButton preOrderBtn;

    @Bind(R.id.mainLogin)
    Button mainLogin;

    @Bind(R.id.mainRegister)
    Button mainRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.preOrder)
    public void skip(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SpecialistsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mainLogin)
    public void login(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mainRegister)
    public void register(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
