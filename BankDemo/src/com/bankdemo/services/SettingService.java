package com.bankdemo.services;

import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.Result;
import com.bankdemo.factory.ResultFactory;

public class SettingService {
    public static Result settingPwd(String pwd){
        CustomerInfo.getInstance().setPassword(pwd);
        System.out.println("密码修改成功！");
        return ResultFactory.settingSuccess(GetString.getSettingMsg());
    }
    public static Result settingPhone(String phone){
        CustomerInfo.getInstance().setPhone(phone);
        System.out.println("修改联系方式成功！");
        return ResultFactory.settingSuccess(GetString.getSettingMsg());
    }
}
