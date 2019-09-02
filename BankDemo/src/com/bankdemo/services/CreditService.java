package com.bankdemo.services;

import com.bankdemo.bean.CustomerInfo;

public class CreditService {
    public static double creditRating(){
        CustomerInfo customer = CustomerInfo.getInstance();
        double money = customer.getBalance().getMoney();
        int level = customer.getCreditLevel().getLevel();
        switch (level){
            case 1:
                customer.getLoan().setLoanLimit(money * 1.0);
                return money * 1.0;
            case 2:
                customer.getLoan().setLoanLimit(money * 1.5);
                return money * 1.5;
            case 3:
                customer.getLoan().setLoanLimit(money * 2.0);
                return money * 2.0;
            case 4:
                customer.getLoan().setLoanLimit(money * 2.5);
                return money * 2.5;
            case 5:
                customer.getLoan().setLoanLimit(money * 3.0);
                return money * 3.0;
            default:
                customer.getLoan().setLoanLimit(0);
                return money * 5.0;

        }
    }
}
