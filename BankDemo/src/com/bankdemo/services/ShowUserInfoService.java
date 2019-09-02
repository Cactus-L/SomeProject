package com.bankdemo.services;

import com.bankdemo.bean.CustomerInfo;

public class ShowUserInfoService {
    public static void showUserInfo(){
        CustomerInfo.getInstance().showInfo();
    }
}
