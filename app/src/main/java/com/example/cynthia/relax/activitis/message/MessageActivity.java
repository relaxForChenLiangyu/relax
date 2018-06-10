package com.example.cynthia.relax.activitis.message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.cynthia.relax.R;
import com.example.cynthia.relax.beans.MessageBean;


import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    private List<MessageBean> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        inputText = (EditText) findViewById(R.id.msg_input);
        send = (Button)findViewById(R.id.btn_send);
        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    MessageBean msg = new MessageBean(content, MessageBean.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息时，刷新recyclerView中的显示
                    adapter.notifyItemInserted(msgList.size()-1);
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    //清空输入框中的内容
                    inputText.setText("");
                }
            }
        });
    }

}
