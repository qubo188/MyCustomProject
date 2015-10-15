package com.qbwc.mytest.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.qbwc.mytest.listener.FragmentCallbackActivityListener;

import butterknife.ButterKnife;

/**
 * 项目名称：MyCustomProject
 * 类描述：所有Activity的基类
 * 创建人：qb
 * 创建时间：2015/10/13 0013 上午 9:58
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener , FragmentCallbackActivityListener {

    public int layoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        operation_before_show_ui();

        setContentView(setLayoutId());

        operation_after_show_ui();

        //Butterknife注入Activity
        ButterKnife.inject(this);

    }


    /**
     * Description 通过传递过来的 布局文件ID。用于显示哪个界面
     */
    protected int setLayoutId() {
        return layoutId;
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

    /**
     * Description fragment回调 Activity里面的方法。用于传递
     */
    @Override
    public void fragmentCallbackActivity() {

    }
    /**
     * Description fragment回调 Activity里面的方法。用于传递
     * @param what 传递 int 类型数据
     */
    @Override
    public void fragmentCallbackActivity(int what) {

    }
    /**
     * Description fragment回调 Activity里面的方法。用于传递
     * @param str 传递 str 类型数据
     */
    @Override
    public void fragmentCallbackActivity(String str) {

    }
    /**
     * Description fragment回调 Activity里面的方法。用于传递
     * @param bundle 传递 bundle 类型数据
     */
    @Override
    public void fragmentCallbackActivity(Bundle bundle) {

    }
}
