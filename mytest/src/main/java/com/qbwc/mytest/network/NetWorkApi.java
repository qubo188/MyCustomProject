package com.qbwc.mytest.network;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectPostRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonPostHttpsRequest;
import com.qbwc.mytest.config.AppContext;

import java.util.Map;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/15 0015 上午 9:19
 */
public class NetWorkApi {


    /**
     * @param reqTag 请求的标识
     * @param reqType 请求的类型 POTS/GET
     * @param reqUrl  请求的地址
     * @param params      传递的参数
     * @param sCallback 成功之后的回调
     * @param eCallback 失败的回调
     * Description ：主要用于分发 POST 和 GET 请求
     */
    public static void requestHttp(String reqTag , String reqType , String reqUrl , Map<String,Object> params , Response.Listener sCallback, Response.ErrorListener eCallback){
        if(reqType.equals("POST")){
            reqPostHttp(reqTag, reqUrl, params, sCallback, eCallback);
        }else{
            reqGetHttp(reqTag, reqUrl, params, sCallback, eCallback);
        }
    }

    /**
     * @param reqTag 请求的标识
     * @param reqUrl  请求的地址
     * @param params      传递的参数
     * @param sCallback 成功之后的回调
     * @param eCallback 失败的回调
     * Description : 创建 Http 的 post请求
     */
    public static void reqPostHttp(String reqTag , String reqUrl , Map<String,Object> params , Response.Listener sCallback, Response.ErrorListener eCallback){
        JsonObjectPostRequest jopr = new JsonObjectPostRequest(reqUrl , sCallback , eCallback , params);
        jopr.setRequestType(reqTag);
        AppContext.reqHttpQueue.add(jopr);
    }

    /**
     * @param reqTag 请求的标识
     * @param reqUrl  请求的地址
     * @param params      传递的参数
     * @param sCallback 成功之后的回调
     * @param eCallback 失败的回调
     * Description 创建 Http 的 get请求
     */
    public static void reqGetHttp(String reqTag , String reqUrl , Map<String,Object> params , Response.Listener sCallback, Response.ErrorListener eCallback){
        JsonObjectRequest jor = new JsonObjectRequest(reqUrl , null , sCallback , eCallback);
        jor.setRequestType(reqTag);
        AppContext.reqHttpQueue.add(jor);
    }

    /**
     * @param reqTag 请求的标识
     * @param reqUrl  请求的地址
     * @param params      传递的参数
     * @param sCallback 成功之后的回调
     * @param eCallback 失败的回调
     * Description 创建 Https 的 get请求
     */
    public static void requestHttps(String reqTag , String reqUrl , Map<String,Object> params , Response.Listener sCallback, Response.ErrorListener eCallback){
        JsonPostHttpsRequest jpr = new JsonPostHttpsRequest(reqUrl , sCallback , eCallback , params);
        jpr.setRequestType(reqTag);
        AppContext.reqHttpsQueue.add(jpr);
    }


}
