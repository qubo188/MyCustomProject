package com.qbwc.mytest.ui;

import android.view.KeyEvent;

import com.qbwc.mytest.base.BaseActivity;
import com.qbwc.mytest.manager.ActivityManager;
import com.qbwc.mytest.utils.PreferencesUtils;

/**
 * 项目名称：MyCustomProject
 * 类描述：此页面主要 功能是 判断当前是第几次进入。所以不需要显示界面。
 *        将判断方法加到 onResume里面
 * 创建人：qubo
 * 创建时间：2015/10/15 0015 上午 11:40
 */
public class StartActivity extends BaseActivity {

    /**
     * Description : 主要功能  用于判断 当前 启动 几次app了
     */
    @Override
    protected void onResume() {
        super.onResume();
        int startCount = (int) PreferencesUtils.getSharedPeferencesValue(this, "startCount", 0);
        if(startCount==0){
            PreferencesUtils.setSharedPeferencesValue(this, "startCount", 1, 0);
            // 第一次进入 加载引导页
            ActivityManager.instance().startNextActivity(GuideActivity.class);
            ActivityManager.instance().popActivity(this);
        }else{
            // TODO: 2015/10/21 0021  第二次进入时。显示本地图片+广告页
        }
    }

    /**
     * @param keyCode
     * @param event
     * @return
     * Description : 禁止 使用 系统 返回键  + Home键 + 菜单键
     */
    @Override
    public boolean onKeyDown(int keyCode , KeyEvent event){
        if(KeyEvent.KEYCODE_BACK==keyCode || KeyEvent.KEYCODE_HOME==keyCode || KeyEvent.KEYCODE_MENU==keyCode){
            return true;
        }
        return super.onKeyDown(keyCode , event);
    }
}
