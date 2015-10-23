package com.qbwc.mytest.config;


/**
 * 项目名称：MyCustomProject
 * 类描述：Application 的全局配置信息
 * 创建人：qb
 * 创建时间：2015/10/13 0013 上午 9:58
 */
public class AppConfig {


    public static final String APP_TAG_NAME = "mytest";
     /*
      *true : 已连接上网络   | false : 没有连接上网络
      */
    public static boolean NETWORK_AVAILABLE_FLAG = false;
    /*
     * 使用的是 哪种网络  wifi 4g  3g gprs
     */
    public static String NETWORK_TYPE = "";
     /*
      *全局  数据库 db名称
      */
    public static final String DB_NAME = "mytest_db";

    public static final String SSL_CONNECT_KEYSTORE_PWD = "yaochizaocan";
    /*
     * 域名
     */
    public static final String NETWOR_KADDRESS = "";

}
