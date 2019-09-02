package com.bankdemo.services;

import com.bankdemo.bean.AllInfo;
import com.bankdemo.bean.UserInfo;

public class CheckServices {
    public static boolean checkeUserId(String id) throws Exception{
        if(AllInfo.getUserInfos().contains(new UserInfo(id))){
            return true;
        }else{
            return false;
        }
    }
    public static boolean checkeUserPwd(String pwd, String realPwd){
        if(realPwd.equals(pwd)){
            return true;
        }else{
            return false;
        }
    }
}
