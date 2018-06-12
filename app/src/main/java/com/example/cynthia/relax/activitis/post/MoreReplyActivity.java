package com.example.cynthia.relax.activitis.post;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.PostBean;
import com.example.cynthia.relax.beans.ReplyBean;
import com.example.cynthia.relax.presenters.PostDetailPresenter;

import java.util.ArrayList;
import java.util.List;

public class MoreReplyActivity extends AppCompatActivity implements MoreReplyView{

    @Bind(R.id.userName)
    TextView userName;

    @Bind(R.id.time)
    TextView time;

    @Bind(R.id.replyContent)
    TextView replyContent;

    @Bind(R.id.replyBtn)
    ImageButton replyBtn;

    @Bind(R.id.reReplyList)
    RecyclerView reReplyList;

    @Bind(R.id.reReplyLayout)
    LinearLayout reReplyLayout;

    @Bind(R.id.commentBtn)
    Button commentBtn;

    @Bind(R.id.editComment)
    EditText editComment;

    private PostDetailPresenter postDetailPresenter;
    private ReplyBean replyBean;
    private List<ReplyBean> replys;
    private LinearLayoutManager linearLayoutManager;
    private PostReplyAdapter postReplyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_reply);
        ButterKnife.bind(this);
        bindData();
        postDetailPresenter = new PostDetailPresenter(this);
        replys = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        postReplyAdapter = new PostReplyAdapter(replys,this,this);
        reReplyList.setLayoutManager(linearLayoutManager);
        reReplyList.setAdapter(postReplyAdapter);

        new Thread(new moreReplyThread()).start();
    }

    @OnClick(R.id.replyBtn)
    public void reply(View view){
        if(reReplyLayout.getVisibility()==View.GONE)
            reReplyLayout.setVisibility(View.VISIBLE);
        else
            reReplyLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.commentBtn)
    public void sendReply(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        Integer userId = sharedPreferences.getInt("userID", 0);
        String content = editComment.getText().toString();
        if(content!=null&&content.length()!=0&&userId!=null) {
            try{
                Integer result = postDetailPresenter.sendReply(userId,replyBean.getPostReplyId(),1,content,0);
            } catch (Exception e) {
                Toast.makeText(MoreReplyActivity.this, "回复失败", Toast.LENGTH_SHORT).show();
            }
        }else if(userId==null){
            Toast.makeText(MoreReplyActivity.this, "请登录", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MoreReplyActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    public void bindData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.replyBean = (ReplyBean) bundle.get("replyBean");
        userName.setText(replyBean.getUserName());
        replyContent.setText(replyBean.getContent());
        time.setText(replyBean.getReplyTime());

    }

    public class moreReplyThread implements Runnable {
        @Override
        public void run() {
            try{
                if(replyBean!=null) {
                    getReplyBeanByReplyId();
                    getReplyListByReplyId();

                }
            }catch (Exception e){
                showFailedMsg("网络连接错误");
            }

        }
    }

    @Override
    public void getReplyBeanByReplyId(){
        postDetailPresenter.getReplyBeanByReplyId(replyBean.getPostReplyId());
    }

    @Override
    public void getReplyListByReplyId(){
        postDetailPresenter.getReplyListByReplyId(replyBean.getPostReplyId());
    }

    @Override
    public void setReplyBean(ReplyBean replyBean) {
        this.replyBean = replyBean;
    }

    @Override
    public void showFailedMsg(String msg) {
        Toast.makeText(MoreReplyActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setReplyList(List<ReplyBean> replyList) {
        this.replys = replyList;
        postReplyAdapter.setReplyList(replys);
        postReplyAdapter.notifyDataSetChanged();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public Integer sendReply(Integer toId,String content){
        Integer result = -1;
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        Integer userId = sharedPreferences.getInt("userID", 0);
        if(content!=null&&content.length()!=0&&userId!=null) {
            try{
                result = postDetailPresenter.sendReply(userId,toId,1,content,0);
            } catch (Exception e) {
                Toast.makeText(MoreReplyActivity.this, "回复失败", Toast.LENGTH_SHORT).show();
            }
        }else if(userId==null){
            Toast.makeText(MoreReplyActivity.this, "请登录", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MoreReplyActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    @Override
    public void hideEdit() {
        editComment.setText("");
        reReplyLayout.setVisibility(View.GONE);
    }
}
