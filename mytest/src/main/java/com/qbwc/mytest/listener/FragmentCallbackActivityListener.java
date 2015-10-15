package com.qbwc.mytest.listener;

import android.os.Bundle;

/**
 * 项目名称：MyCustomProject
 * 类描述：此类主要 BaseActivity实现此接口 ， 用于 Activity显示 Fragment之后，在Fragment回调Activity的方法
 * 创建人：qubo
 * 创建时间：2015/10/13 0013 下午 12:56
 */
public interface FragmentCallbackActivityListener {

    /*
     * fragment 回调 Activity里面的方法
     */
    void fragmentCallbackActivity();
    void fragmentCallbackActivity(int what);
    void fragmentCallbackActivity(String str);
    void fragmentCallbackActivity(Bundle bundle);
}
