package com.example.cynthia.relax.activitis.post;

import android.content.Context;

public interface SendPostView {
    Context context();
    void showFailedMsg(String msg);
    void redict();
}
