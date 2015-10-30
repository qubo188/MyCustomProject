package com.qbwc.mytest.ui;

import android.view.KeyEvent;

import com.qbwc.mytest.R;
import com.qbwc.mytest.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean availableStatusBar(){
        return false;
    }

    @Override
    public void operation_after_show_ui(){
    }


    @Override
    public boolean onKeyDown(int keyCode , KeyEvent event){
        if()
    }

}
