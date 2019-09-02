package com.bankdemo.services;

import com.bankdemo.bean.Balance;
import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.Result;
import com.bankdemo.factory.ResultFactory;

public class DepositService {
    public static Result deposit(double money){
        CustomerInfo customer = CustomerInfo.getInstance();
        Balance originBalance = customer.getBalance();
        customer.setBalance(new Balance(originBalance.getMoney() + money, money, 0, 0));
        String msg = GetString.getDepositMsg();
        System.out.println(msg);
        return ResultFactory.depositSuccess(msg);
    }
}
