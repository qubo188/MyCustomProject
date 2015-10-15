package com.qbwc.mytest.config;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.qbwc.mytest.dao.DaoMaster;
import com.qbwc.mytest.dao.DaoSession;

/**
 * 项目名称：MyCustomProject
 * 类描述：Application 的上下文配置
 * 创建人：qb
 * 创建时间：2015/10/13 0013 上午 9:58
 */
public class AppContext extends Application {

    //Application 上下文对象
    private static AppContext appContext;
    //greenDao的核心控制器
    private static DaoMaster daoMaster;
    //greenDao的主要操作工具类
    private static DaoSession daoSession;
    // Volley只需要 初始化 1次 RequestHttpQueue;
    public static RequestQueue reqHttpQueue;
    public static RequestQueue reqHttpsQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        if (appContext == null) appContext = this;
        reqHttpQueue = Volley.newRequestQueue(this);
       /* reqHttpsQueue = Volley.newHttpsRequestQueue(getApplicationContext(),
                new SslHttpStack(new SslHttpClient(this, R.raw.android_user, AppConfig.SSL_CONNECT_KEYSTORE_PWD)));*/
    }


    /**
     * @param context
     * @return daoMaster 返回 全局的 数据库 连接的对象
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(context, AppConfig.DB_NAME, null);
            daoMaster = new DaoMaster(openHelper.getReadableDatabase());
        }
        return daoMaster;
    }

    /**
     * @param context
     * @return 返回 核心的 具体 操作 数据库表的 工具对象
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}