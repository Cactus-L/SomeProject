package com.bankdemo.factory;

import com.bankdemo.bean.Result;

public class ResultFactory {
    public static Result resultFactory(int code, String msg){
        return new Result(code, msg);
    }
    public static Result loanSuccess(String msg){
        return new Result(31, msg);
    }
    public static Result transferSuccess(String msg){
        return new Result(11, msg);
    }
    public static Result depositSuccess(String msg){
        return new Result(21, msg);
    }
    public static Result repaymentSuccess(String msg){
        return new Result(41, msg);
    }
    public static Result settingSuccess(String msg){
        return new Result(51, msg);
    }

}
