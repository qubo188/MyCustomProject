package com.qbwc.mytest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.qbwc.mytest.config.AppConfig;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/15 0015 上午 11:27
 */
public class NetWorkUtils {
    private static ConnectivityManager cm;


    /**
     * @param context 上下文
     * @return true :已经连接上网络    false：还没有连接上网络
     * Description :判断网络是否连接
     */
    public static boolean netWorkAvailable(Context context) {
        if (cm == null) {
            cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return netWorkAvailable(cm.getActiveNetworkInfo());
    }

    /**
     * @param netWorkInfo 网络 信息
     * @return true :已经连接上网络    false：还没有连接上网络
     * Description 主要判断 当前网络是否连接上
     */
    public static boolean netWorkAvailable(NetworkInfo netWorkInfo) {
        if (netWorkInfo == null) {
            AppConfig.NETWORK_AVAILABLE_FLAG = false;
            return false;
        } else {
            AppConfig.NETWORK_AVAILABLE_FLAG = false;
            netWorkType(netWorkInfo);
            return true;
        }
    }

    /**
     * @param networkInfo
     * @return 当前 使用的 那种 网络 连接  wifi mobile
     * 如果无网络返回  network-no
     * Description : 此函数 主要用 获取 网络 的类型
     * 及 设置 global variable
     */
    public static String netWorkType(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            AppConfig.NETWORK_TYPE = "network-no";
            return "network-no";
        } else {
            String networkType = networkInfo.getTypeName();
            AppConfig.NETWORK_TYPE = networkType;
            return networkType;
        }
    }

    /**
     * @param context 上下文
     * @return true 网络情况好。允许请求网络。或者 下载等操作  。
     * @Description 由于手机的网络 分为 4g 3g 2g gprs...等 。所以在手机网络状态好的情况下。我允许执行一些网络操作
     */
    public static boolean mobileNetWorkFast(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (tm.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }

}
