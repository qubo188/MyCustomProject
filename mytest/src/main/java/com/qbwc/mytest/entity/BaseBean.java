package com.qbwc.mytest.entity;

/**
 * 项目名称：MyCustomProject
 * 类描述：统一最外层 的数据 结构
 * 创建人：qubo
 * 创建时间：2015/10/26 0026 上午 11:58
 */
public class BaseBean {
    private int ret_code;
    private String err_msg;
    private String data;

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
