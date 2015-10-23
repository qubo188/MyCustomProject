package com.qbwc.mytest.utils;

import android.util.Log;

import com.qbwc.mytest.config.AppConfig;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/23 0023 下午 4:30
 */
public class LogUtils {


    public static final void pError(String msg){
        Log.e(AppConfig.APP_TAG_NAME , "error--->"+msg);
    }

    public static final void pInfo(String msg){
        Log.i(AppConfig.APP_TAG_NAME, "info--->" + msg);
    }
    public static final void pdebug(String msg){
        Log.i(AppConfig.APP_TAG_NAME , "debug--->" + msg);
    }
}
