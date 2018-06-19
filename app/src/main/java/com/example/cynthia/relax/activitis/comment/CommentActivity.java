package com.example.cynthia.relax.activitis.comment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.activitis.historyorder.HistoryOrderActivity;
import com.example.cynthia.relax.activitis.orderdetail.OrderDetailActivity;
import com.example.cynthia.relax.presenters.CommentPresenter;

public class CommentActivity extends BaseActivity implements CommentView {

    @Bind(R.id.CspecialistName)
    TextView specialistName;
    @Bind(R.id.CratingBar)
    RatingBar ratingBar;
    @Bind(R.id.commentContent)
    EditText comment;
    @Bind(R.id.commentBtn)
    Button commentBtn;
    @Bind(R.id.cancelComment)
    Button cancelComment;

    Intent intent;
    CommentPresenter commentPresenter;
    String orderId = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initializeTop(this, true, "订单评价");
        ButterKnife.bind(this);

        commentPresenter = new CommentPresenter(this);
        intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        specialistName.setText(intent.getStringExtra("specialistName"));
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentPresenter.submitComment(orderId, ratingBar.getRating(),comment.getText().toString());
            }
        });
        cancelComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(CommentActivity.this,OrderDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void commentFinishedIntent() {
        startActivity(new Intent(CommentActivity.this,HistoryOrderActivity.class));
    }

    @Override
    public void showMsg(String message) {
            Toast.makeText(CommentActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
