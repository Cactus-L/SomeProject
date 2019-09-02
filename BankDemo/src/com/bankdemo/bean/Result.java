package com.bankdemo.bean;

public class Result {
    int resCode;
    String msg;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result(int resCode, String msg) {
        this.resCode = resCode;
        this.msg = msg;
    }

    public Result(int resCode) {
        this.resCode = resCode;
    }

    public Result(){
    }

    @Override
    public String toString() {
        return "Result{" +
                "resCode=" + resCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
