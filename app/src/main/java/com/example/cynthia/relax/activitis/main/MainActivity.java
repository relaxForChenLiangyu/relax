package com.example.cynthia.relax.activitis.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.historyorder.HistoryOrderActivity;
import com.example.cynthia.relax.activitis.login.LoginActivity;
import com.example.cynthia.relax.activitis.order.OrderActivity;
import com.example.cynthia.relax.activitis.personal.PersonalActivity;
import com.example.cynthia.relax.activitis.personal.PersonalCommentsActivity;
import com.example.cynthia.relax.activitis.personal.QualifyActivity;
import com.example.cynthia.relax.activitis.register.RegisterActivity;
import com.example.cynthia.relax.activitis.specialists.SpecialistsActivity;
import com.example.cynthia.relax.beans.UserBean;
import com.example.cynthia.relax.presenters.MainPresenter;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainView{
    @Bind(R.id.preOrder)
    ImageButton preOrderBtn;

    @Bind(R.id.myOrders)
    ImageButton myOrdersBtn;

    @Bind(R.id.mainLogin)
    Button mainLogin;

    @Bind(R.id.mainRegister)
    Button mainRegister;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    //@Bind(R.id.navUserName)
    TextView navUserName;

    private SharedPreferences sharedPreferences;
    private int curUserId = 0;
    private MainPresenter mainPresenter;
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24);
        }
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        curUserId = sharedPreferences.getInt("userID", 0);
        userBean = new UserBean();

        mainPresenter = new MainPresenter(this);
        mainPresenter.getCurrentUserInfo(curUserId);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()){
                    case R.id.navMyOrders:
                        if(curUserId==0)
                            intent.setClass(MainActivity.this, LoginActivity.class);
                        else {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userBean",userBean);
                            intent.putExtras(bundle);
                            intent.setClass(MainActivity.this, HistoryOrderActivity.class);
                        }
                        startActivity(intent);
                        break;
                    case R.id.navMyInfo:
                        if(curUserId==0)
                            intent.setClass(MainActivity.this, LoginActivity.class);
                        else {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userBean",userBean);
                            intent.putExtras(bundle);
                            intent.setClass(MainActivity.this, PersonalActivity.class);
                        }
                        startActivity(intent);
                        break;
                    case R.id.navMyComment:
                        if(curUserId==0)
                            intent.setClass(MainActivity.this, LoginActivity.class);
                        else {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userBean",userBean);
                            intent.putExtras(bundle);
                            intent.setClass(MainActivity.this, PersonalCommentsActivity.class);
                        }
                        startActivity(intent);
                        break;
                    case R.id.navIdentify:
                        if(curUserId==0)
                            intent.setClass(MainActivity.this, LoginActivity.class);
                        else {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userBean",userBean);
                            intent.putExtras(bundle);
                            intent.setClass(MainActivity.this, QualifyActivity.class);
                        }
                        startActivity(intent);
                        break;
                    case R.id.contactUs:
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                navUserName = (TextView) navigationView.findViewById(R.id.navUserName);
                if(userBean!=null)
                    navUserName.setText(userBean.getNickname());
                else
                    navUserName.setText("");
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @OnClick(R.id.preOrder)
    public void preOrder(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SpecialistsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.myOrders)
    public void myOrders(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, HistoryOrderActivity.class);
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

    @Override
    public void showFailedMsg(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
