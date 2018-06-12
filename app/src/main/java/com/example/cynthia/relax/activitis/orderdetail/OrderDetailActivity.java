package com.example.cynthia.relax.activitis.orderdetail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.comment.CommentActivity;
import com.example.cynthia.relax.activitis.historyorder.HistoryOrderActivity;
import com.example.cynthia.relax.activitis.login.LoginActivity;
import com.example.cynthia.relax.activitis.register.RegisterActivity;
import com.example.cynthia.relax.beans.OrderBean;
import com.example.cynthia.relax.beans.OrderStatus;
import com.example.cynthia.relax.beans.Type;
import com.example.cynthia.relax.presenters.LoginPresenter;
import com.example.cynthia.relax.presenters.OrderDetailPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailView {

    @Bind(R.id.DorderTime)
    TextView orderTime;
    @Bind(R.id.DorderStatus)
    TextView orderStatus;
    @Bind(R.id.DconsultingType)
    TextView consultType;
    @Bind(R.id.DpatientName)
    TextView patientName;
    @Bind(R.id.DspecialistName)
    TextView specialistName;
    @Bind(R.id.DspecialistPhone)
    TextView specialistPhone;
    @Bind(R.id.DconsultingStartTime)
    TextView consultingStartTime;
    @Bind(R.id.DconsultingEndTime)
    TextView consultingEndTime;
    @Bind(R.id.Ddescription)
    TextView description;
    @Bind(R.id.cancelBtn)
    Button cancelBtn;
    @Bind(R.id.operateBtn)
    Button operateBtn;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private SharedPreferences sharedPreferences;
    private OrderDetailPresenter orderDetailPresenter;
    Intent intent;
    int identity = 0;
    String orderId = "1";

    @Override
    public void showMsg(String message) {
        Toast.makeText(OrderDetailActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("订单详情");
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        orderDetailPresenter = new OrderDetailPresenter(this);
        intent = getIntent();
        String orderId = intent.getStringExtra("orderId");
        identity = sharedPreferences.getInt("identity", 0);
        orderDetailPresenter.showOrder(orderId);
    }

    @Override
    public void showDatas(final OrderBean orderBean) {
        Date pd = new Date(orderBean.getPubishTime());
        Date sd = new Date(orderBean.getConsultingStartTime());
        Date ed = new Date(orderBean.getConsultingFinishTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderTime.setText(formatter.format(pd));
        orderStatus.setText(OrderStatus.getStatusByIndex(orderBean.getOrderStatus()));
        consultType.setText(Type.getTypeByIndex(orderBean.getTypeId()));
        patientName.setText(orderBean.getPatientName());
        specialistName.setText(orderBean.getSpecialistName());
        specialistPhone.setText(orderBean.getSpecialistPhone());
        consultingStartTime.setText(formatter.format(sd));
        consultingEndTime.setText(formatter.format(ed));
        description.setText(orderBean.getDescription());
        if (orderBean.getOrderStatus() == 1) {
            if (identity == 0)
                cancelBtn.setText("取消预订");
            else
                cancelBtn.setText("拒绝预订");
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderDetailPresenter.cancelOrder(orderId);
                }
            });
        } else
            cancelBtn.setVisibility(View.INVISIBLE);
        if (identity == 0) {
            switch (orderBean.getOrderStatus()) {
                case 3:
                    operateBtn.setText("去付款");
                    operateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orderDetailPresenter.continueOrder(orderId);
                        }
                    });
                    break;
                case 4:
                    operateBtn.setText("咨询开始");
                    operateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orderDetailPresenter.continueOrder(orderId);
                        }
                    });
                    break;
                case 7:
                    operateBtn.setText("咨询结束");
                    operateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orderDetailPresenter.continueOrder(orderId);
                        }
                    });
                    break;
                case 8:
                    operateBtn.setText("去评价");
                    operateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(OrderDetailActivity.this, CommentActivity.class);
                            intent.putExtra("orderId",orderId);
                            intent.putExtra("specialistName",orderBean.getSpecialistName());
                            startActivity(intent);
                        }
                    });
                    break;
                default:
                    operateBtn.setVisibility(View.INVISIBLE);
            }
        }
        if (identity == 1) {
            switch (orderBean.getOrderStatus()) {
                case 1:
                    operateBtn.setText("接单");
                    operateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orderDetailPresenter.continueOrder(orderId);
                        }
                    });
                    break;
                case 4:
                    operateBtn.setText("咨询开始");
                    operateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orderDetailPresenter.continueOrder(orderId);
                        }
                    });
                    break;
                case 7:
                    operateBtn.setText("咨询结束");
                    operateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orderDetailPresenter.continueOrder(orderId);
                        }
                    });
                    break;
                default:
                    operateBtn.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void statusChangedIntent() {
        startActivity(new Intent(OrderDetailActivity.this, HistoryOrderActivity.class));
    }
}
