package com.bankdemo.services;

import com.bankdemo.bean.AllInfo;
import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.UserInfo;
import com.bankdemo.io.ReadXls;
import com.bankdemo.bean.Frozen;

import java.util.List;
import java.util.Scanner;

public class LoginService {
    public static void login(Scanner sc) throws Exception{
        CustomerInfo customerInfo = CustomerInfo.getInstance();
        String strId = "";
        String strPwd = "";
        int pwdFlag = 0;
        boolean idFlag = true;
        System.out.println("欢迎使用本ATM机,请登录：");
        Frozen.setFrozenList(ReadXls.readFrozen());
        while (idFlag){
            System.out.println("请输入您的账号：");
            strId = sc.nextLine();
            if (CheckServices.checkeUserId(strId)){
                if(!Frozen.isFrozen(strId)){
                    List<UserInfo> userInfos = AllInfo.getUserInfos();
                    UserInfo u = userInfos.get(userInfos.indexOf(new UserInfo(strId)));
                    idFlag = false;
                    System.out.println("请输入您的密码：");
                    for (; pwdFlag < 3; pwdFlag++ ){
                        strPwd = sc.nextLine();
                        if(CheckServices.checkeUserPwd(strPwd, u.getPassword())){
                            break;
                        }else{
                            if(pwdFlag != 2){
                                System.out.println("密码输入错误，请重新输入:");
                            }
                        }
                    }
                    if(pwdFlag < 3){
                        CustomerInfo.initCustomer(u);
                    }else{
                        Frozen.add2FrozenList(strId);
                        System.out.println("您的账户已被冻结  ");
                        pwdFlag = 0;
                        idFlag = true;
                    }
                }else{
                    System.out.println("您的账号已被冻结，请尽快联系工作人员。");
                }

            }else{
                System.out.println("账号输入错误,请重新输入");
            }
        }
    }
}
