package com.example.pengervideo.bean;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class CodeBoy {
    /**
     * msg : token已过期
     * code : 2003
     */

    private String msg;
    private int code;
    private Object date;

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
