package com.bankdemo.services;

import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.Loan;
import com.bankdemo.bean.Result;
import com.bankdemo.factory.ResultFactory;

public class EarlyRepaymentService {
    public static Result earlyRepayment(double sum, double money){
        CustomerInfo customer = CustomerInfo.getInstance();
        Loan loan = customer.getLoan();
        if(money >= sum){
            customer.setLoan(new Loan(0,0,0,0,0,0));
            customer.getBalance().setAddMoney(money - sum);
            customer.getBalance().setMoney(customer.getBalance().getMoney() + money - sum);
            customer.getCreditLevel().setLevel(customer.getCreditLevel().getLevel() + 1);
            System.out.println("提前还款成功，您的余额增加 " + (money - sum) + " 元");
            System.out.println("您的信用额度为： " + customer.getCreditLevel().getLevel());
            String msg = GetString.getRepayment1Msg(money, sum);
            System.out.println(msg);
            return ResultFactory.repaymentSuccess(msg);
        }else{
            customer.getLoan().setLoanValue(sum - money);
            System.out.println("您已还款 " + money + "元，您还需要还款 " + (sum - money) + "元");
            String msg = GetString.getRepayment1Msg(money, sum);
            System.out.println(msg);
            return ResultFactory.repaymentSuccess(msg);
        }
    }
}
