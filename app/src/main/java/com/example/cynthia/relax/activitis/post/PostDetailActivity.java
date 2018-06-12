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
import com.example.cynthia.relax.activitis.BaseActivity;
import com.example.cynthia.relax.beans.PostBean;
import com.example.cynthia.relax.beans.ReplyBean;
import com.example.cynthia.relax.presenters.PostDetailPresenter;

import java.util.ArrayList;
import java.util.List;

public class PostDetailActivity extends BaseActivity implements PostDetailView{

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.author)
    TextView author;

    @Bind(R.id.time)
    TextView time;

    @Bind(R.id.content)
    TextView content;

    @Bind(R.id.replyBtn)
    Button replyBtn;

    @Bind(R.id.noReply)
    TextView noReply;

    @Bind(R.id.replyList)
    RecyclerView replyList;

    @Bind(R.id.reLayout)
    LinearLayout reLayout;

    @Bind(R.id.editComment)
    EditText editComment;

    @Bind(R.id.commentBtn)
    Button commentBtn;
    
    private PostDetailPresenter postDetailPresenter;
    private PostBean postBean;
    private List<ReplyBean> replys;
    private LinearLayoutManager linearLayoutManager;
    private PostReplyAdapter postReplyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initializeTop(this, true, "问题详情");
        ButterKnife.bind(this);
        bindData();
        postDetailPresenter = new PostDetailPresenter(this);
        replys = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        postReplyAdapter = new PostReplyAdapter(replys,this,this);
        replyList.setLayoutManager(linearLayoutManager);
        replyList.setAdapter(postReplyAdapter);

        new Thread(new postDetailThread()).start();
    }

    @OnClick(R.id.commentBtn)
    public void sendReply(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        Integer userId = sharedPreferences.getInt("userID", 0);
        String content = editComment.getText().toString();
        if(content!=null&&content.length()!=0&&userId!=null) {
            try{
                Integer result = postDetailPresenter.sendReply(userId,postBean.getPostId(),0,content,1);
            } catch (Exception e) {
                Toast.makeText(PostDetailActivity.this, "回复失败", Toast.LENGTH_SHORT).show();
            }
        }else if(userId==null){
            Toast.makeText(PostDetailActivity.this, "请登录", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(PostDetailActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.replyBtn)
    public void reply(View view){
        if(reLayout.getVisibility()==View.GONE)
            reLayout.setVisibility(View.VISIBLE);
        else
            reLayout.setVisibility(View.GONE);
    }

    public class postDetailThread implements Runnable {
        @Override
        public void run() {
            try{
                if(postBean!=null) {
                    getPostBeanByPostId();
                    getReplyListByPostId();

                }
            }catch (Exception e){
                showFailedMsg("网络连接错误");
            }

        }
    }

    public void bindData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.postBean = (PostBean) bundle.get("postBean");
        title.setText(postBean.getTitle());
        author.setText(postBean.getUserName());
        time.setText(postBean.getTime());
        content.setText(postBean.getContent());

    }

    @Override
    public void getPostBeanByPostId(){
        postDetailPresenter.getPostBeanByPostId(postBean.getPostId());
    }

    @Override
    public void getReplyListByPostId(){
        postDetailPresenter.getReplyListByPostId(postBean.getPostId());
    }
    
    @Override
    public void setPostBean(PostBean postBean) {
        this.postBean = postBean;
    }

    @Override
    public void showFailedMsg(String msg) {
        Toast.makeText(PostDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNoReplyText(String msg) {
        noReply.setText(msg);
        noReply.setVisibility(View.VISIBLE);

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
                result = postDetailPresenter.sendReply(userId,toId,1,content,1);
            } catch (Exception e) {
                Toast.makeText(PostDetailActivity.this, "回复失败", Toast.LENGTH_SHORT).show();
            }
        }else if(userId==null){
            Toast.makeText(PostDetailActivity.this, "请登录", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(PostDetailActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    @Override
    public void hideEdit() {
        editComment.setText("");
        reLayout.setVisibility(View.GONE);
    }
}
