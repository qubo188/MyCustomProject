package com.qbwc.mytest.ui.widget;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qbwc.mytest.base.BaseActivity;
import com.qbwc.mytest.manager.ActivityManager;
import com.qbwc.mytest.ui.MainActivity;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/29 0029 下午 2:06
 */
public class CountDown<T extends BaseActivity> extends CountDownTimer{

    // 显示描述的 控件
    private TextView tv;
    // 显示 广告 或者其它的ImageView
    private ImageView iv;
    // Activity的对象
    private T context;
    // 是谁启动的 倒计时 标识
    private String Tag;

    /**
     * @param millisInFuture  显示的秒数
     * @param countDownInterval 多少时间切换一次秒数
     * @param tv  显示秒数的空间
     * @param iv  显示广告的ImageView
     * @param context Activity的对象
     * @param Tag   是谁开启的这个广告 标识
     */
    public CountDown(long millisInFuture, long countDownInterval , TextView tv , ImageView iv , T context ,String Tag){
        super(millisInFuture, countDownInterval);
        this.tv = tv;
        this.iv = iv;
        this.context = context;
        this.Tag = Tag;
    }



    @Override
    public void onTick(long millisUntilFinished) {
        tv.setText(millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {
        if(Tag.equals("local_default")){
        tv.setVisibility(View.GONE);
        iv.setVisibility(View.GONE);
        context.fragmentCallbackActivity(0);}
        else{
            ActivityManager.instance().startNextActivity(MainActivity.class);
        }
    }
}
