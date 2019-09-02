package com.bankdemo.services;

import com.bankdemo.factory.ResultFactory;
import com.bankdemo.bean.*;
import com.bankdemo.bean.Result;
import com.bankdemo.bean.ResultEnum;

import java.util.List;
import java.util.Scanner;

public class TransferService {
    public static Result transferAccounts(String id, double money, Scanner sc) throws Exception{
        List<UserInfo> userInfos = AllInfo.getUserInfos();
        if(userInfos.contains(new UserInfo(id))){
            CustomerInfo customer = CustomerInfo.getInstance();
            UserInfo receiver = userInfos.get(userInfos.indexOf(new UserInfo(id)));
            double tOriginMoney = customer.getBalance().getMoney();
            double rOriginMoney = receiver.getBalance().getMoney();
            System.out.println("您将转给【" + receiver.getUserName() + "】,请确认无误后转账！(回复1确认，0取消）");
            int in = sc.nextInt();
            if(1 == in){
                    if(tOriginMoney >= money){
                        customer.setBalance(new Balance(tOriginMoney - money, 0 , money, 0));
                        receiver.setBalance(new Balance(rOriginMoney + money, money, 0 ,(customer.getBalance().getModify()+1)));
                        String str = GetString.getTransferMsg(receiver);
                        System.out.println(str);
                        return ResultFactory.transferSuccess(str);
                    }else{
                        System.out.println("账户余额不足");
                        return ResultFactory.resultFactory(ResultEnum.BalanceNoEnough.getResCode(),ResultEnum.BalanceNoEnough.getMsg());
                    }
                }else{
                    System.out.println("您已取消本次业务！");
                    return ResultFactory.resultFactory(ResultEnum.Invalid.getResCode(),ResultEnum.Invalid.getMsg());
                }
        }else{
            System.out.println("转账用户不存在");
            return ResultFactory.resultFactory(ResultEnum.Invalid.getResCode(),ResultEnum.Invalid.getMsg());
        }
    }
}
