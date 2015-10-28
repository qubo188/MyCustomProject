package com.qbwc.mytest.utils;

import com.alibaba.fastjson.JSON;
import com.qbwc.mytest.entity.BaseBean;

import java.util.List;

/**
 * 项目名称：MyCustomProject
 * 类描述：主要 用于 解析  各种 请求 网络 返回的 数据
 * 创建人：qubo
 * 创建时间：2015/10/28 0028 上午 10:43
 */
public class ParseDataUtils {

    /**
     * @param cls  要解析 成 的对象
     * @param str  被解析的  json字符串
     * @param <T>
     * @return
     */
    public static <T extends BaseBean> T parse_StrToBean(Class<T> cls, String str){
        return (T)JSON.parseObject(str, cls);
    }

    /**
     * @param bean  被解析的一个对象。
     * @return true :数据请求成功
     *         false:数据请求失败
     *
     * Description : 判断数据 请求 成功与否
     */
    public static <T extends BaseBean> boolean parseSucOrErr(T bean){
        return bean.getRet_code()>=0 ? true : false;
    }

    /**
     * @param cls 转换的 具体类
     * @param str 被转换的 字符串
     * @return result结果集
     */
    public static <T extends BaseBean> List<T> parseDataList(Class<T> cls ,String str){
        return JSON.parseArray(str , cls);
    }
}
