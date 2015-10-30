package com.qbwc.mytest.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.qbwc.mytest.listener.FragmentCallbackActivityListener;
import com.qbwc.mytest.manager.ActivityManager;

import butterknife.ButterKnife;

/**
 * 项目名称：MyCustomProject
 * 类描述：所有Activity的基类
 * 创建人：qb
 * 创建时间：2015/10/13 0013 上午 9:58
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener, FragmentCallbackActivityListener {

    public int layoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //显示布局之前的操作
        operation_before_show_ui();

        //是否隐藏状态栏
        if (!availableStatusBar()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
            Window w = this.getWindow();
            w.setFlags(flag , flag);
        }

        if (setLayoutId() != 0) setContentView(setLayoutId());

        //Butterknife注入Activity
        ButterKnife.bind(this);
        //初始化  Fresco
        Fresco.initialize(this);

        // 显示布局之后的操作
        operation_after_show_ui();
        ActivityManager.instance().pushActivity(this);
    }


    /**
     * Description 通过传递过来的 布局文件ID。用于显示哪个界面
     */
    protected int setLayoutId() {
        return layoutId;
    }

    /*
     * Description : status bar  是否 可用  DefaultValue:false <br>
     *               true:显示 statusBar
     *               false:不显示 statusBar
     *
     */
    public boolean availableStatusBar() {
        return false;
    }

    @Override
    public void onClick(View v) {

    }


    /**
     * Description 在最终显示界面之前做的操作
     */
    public void operation_before_show_ui() {

    }

    /**
     * Description 操作 显示之后的 ui
     */
    protected void operation_after_show_ui() {

    }

    @Override
    public <T> void fragmentCallbackActivity(T who) {

    }
}
