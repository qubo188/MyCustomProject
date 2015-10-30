package com.qbwc.mytest.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectPostRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonPostHttpsRequest;
import com.qbwc.mytest.config.AppContext;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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
     * @param reqUrl  请求的地址
     * @param params      传递的参数
     * @param sCallback 成功之后的回调
     * @param eCallback 失败的回调
     * Description : 创建 Http 的 post请求
     */
    public static void reqPostHttp(String reqTag , String reqUrl , Map<String,Object> params , Response.Listener sCallback, Response.ErrorListener eCallback){
        JsonObjectPostRequest jopr = new JsonObjectPostRequest(reqUrl , sCallback , eCallback , params){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data, "UTF-8");
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        jopr.setRequestType(reqTag);
        AppContext.reqHttpQueue.add(jopr);
    }

    /**
     * @param reqTag 请求的标识
     * @param reqUrl  请求的地址
     * @param sCallback 成功之后的回调
     * @param eCallback 失败的回调
     * Description 创建 Http 的 get请求
     */
    public static void reqGetHttp(String reqTag , String reqUrl  , Response.Listener sCallback, Response.ErrorListener eCallback){
        JsonObjectRequest jor = new JsonObjectRequest(reqUrl , null , sCallback , eCallback){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data, "UTF-8");
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
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
        JsonPostHttpsRequest jpr = new JsonPostHttpsRequest(reqUrl , sCallback , eCallback , params){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data, "UTF-8");
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        jpr.setRequestType(reqTag);
        AppContext.reqHttpsQueue.add(jpr);
    }


}
