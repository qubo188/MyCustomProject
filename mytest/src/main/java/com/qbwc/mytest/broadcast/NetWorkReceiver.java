package com.qbwc.mytest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qbwc.mytest.utils.NetWorkUtils;

/**
 * 项目名称：MyCustomProject
 * 类描述：1 监听网络是否连接的状态
 * 2 判断当前使用的是哪种网络类型
 * 创建人：qubo
 * 创建时间：2015/10/14 0014 下午 4:57
 */
public class NetWorkReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NetWorkUtils.netWorkAvailable(context);
    }


}
