package com.bankdemo.bean;

import com.bankdemo.io.ReadXls;

import java.util.ArrayList;
import java.util.List;

public class AllInfo {
    private static List<UserInfo> userInfos = new ArrayList<>();
    public static List<UserInfo> getUserInfos() throws Exception{
        if(userInfos.isEmpty()){
            System.out.println("（读取本地所有用户数据完成）");
            userInfos = ReadXls.readXls();
            return userInfos;
        }else{
            return userInfos;
        }
    }
}

