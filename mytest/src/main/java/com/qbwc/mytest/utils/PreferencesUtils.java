package com.qbwc.mytest.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.qbwc.mytest.config.AppConfig;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/14 0014 下午 4:59
 */
public class PreferencesUtils {


    /**
     * @param context 上下文
     * @param key     要查询的 关键字
     * @param mode    查询的类型  <br>
     *                0 ： int<br>
     *                1 : long<br>
     *                2 : String<br>
     *                3 : boolean<br>
     *                4 : float<br>
     * @return 查询的结果<br>
     *                return int defaultValue : 0;<br>
     *                return long defaultValue : 0;<br>
     *                return String defaultValue : "";<br>
     *                return boolean defaultValue : false;<br>
     *                return float defaultValue : 0f;
     *
     * Description : 主要获取 SharedReferences里面的值
     */
    public static Object getSharedPeferencesValue(Context context, String key, int mode) {
        SharedPreferences sp = context.getSharedPreferences(AppConfig.APP_TAG_NAME, 0);
        Object o = new Object();
        switch (mode) {
            case 0:
                o = sp.getInt(key, 0);
                break;
            case 1:
                o = sp.getLong(key, 0);
                break;
            case 2:
                o = sp.getString(key, "");
                break;
            case 3:
                o = sp.getBoolean(key, false);
                break;
            case 4:
                o = sp.getFloat(key, 0f);
                break;
            default:
                o = null;
                break;
        }
        return o;
    }

    /**
     * @param context 上下文
     * @param key     存储数据的 关键字
     * @param value   具体存储的值
     * @param mode    查询的类型
     *                0 ： int<br>
     *                1 : long<br>
     *                2 : String<br>
     *                3 : boolean<br>
     *                4 : float<br>
     *
     * Description : 主要往 SharedReferences 存值
     */
    public static void setSharedPeferencesValue(Context context , String key , Object value , int mode){
        SharedPreferences sp = context.getSharedPreferences(AppConfig.APP_TAG_NAME , 0);
        SharedPreferences.Editor editor = sp.edit();
        switch(mode){
            case 0:
                editor.putInt(key , (int)value);
                break;
            case 1:
                editor.putLong(key , (long)value);
                break;
            case 2:
                editor.putString(key , (String)value);
                break;
            case 3:
                editor.putBoolean(key ,(boolean)value);
                break;
            case 4:
                editor.putFloat(key , (float)value);
                break;
            default:
                break;
        }
        editor.commit();
    }


}

