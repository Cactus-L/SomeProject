package com.bankdemo.services;

import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.Result;
import com.bankdemo.bean.ResultEnum;
import com.bankdemo.factory.ResultFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ApplyLoanService {
    public static Result applyLoan1(double applyMoney, double applyLimit, int day){
        if(applyMoney > applyLimit){
            System.out.println("贷款失败，贷款数额超出信用额度");
            return new Result(ResultEnum.LoanOutOfLimit.getResCode(), ResultEnum.LoanOutOfLimit.getMsg());
        }else{
            CustomerInfo customer = CustomerInfo.getInstance();
            Date now = new Date();
            Calendar c =Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR , day);
            Date back = c.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            customer.getLoan().setLoanValue(applyMoney);
            customer.getLoan().setApplyDay(day);
            customer.getLoan().setRate(day/Double.valueOf(1440.00));//五年翻一倍
            customer.getLoan().setStartDay(now.getTime());
            customer.getLoan().setBackTime(back.getTime());
            customer.getLoan().setModel(1);
            customer.getBalance().setMoney(customer.getBalance().getMoney() + applyMoney);
            customer.getBalance().setAddMoney(applyMoney);
            String msg = GetString.getLoanMsg();
            System.out.println(msg);
            return ResultFactory.loanSuccess(msg);
        }
    }
    public static Result amortizationLoan(double applyMoney, double applyLimit, int day, int term){
        if(applyMoney > applyLimit){
            System.out.println("贷款失败，贷款数额超出信用额度");
            return new Result(ResultEnum.LoanOutOfLimit.getResCode(), ResultEnum.LoanOutOfLimit.getMsg());
        }else {
            CustomerInfo customer = CustomerInfo.getInstance();
            Date now = new Date();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, day);
            Date back = c.getTime();
            double sum = (customer.getLoan().getRate() * applyMoney) + applyMoney;
            double partmoney = sum / term; //金额数分配
            double partDay = day / term; //每期间隔天数
            customer.getLoan().setLoanValue(applyMoney);
            customer.getLoan().setApplyDay(day);
            customer.getLoan().setRate(day / Double.valueOf(1440.00));//五年翻一倍
            customer.getLoan().setStartDay(now.getTime());
            customer.getLoan().setBackTime(back.getTime());
            customer.getLoan().setModel(term);
            customer.getLoan().setPartValue(partmoney);
            customer.getBalance().setMoney(customer.getBalance().getMoney() + applyMoney);
            customer.getBalance().setAddMoney(applyMoney);
            Date nextTerm = now;
            for(int i = 0; i < term; i++){
                c.add(Calendar.DAY_OF_YEAR, (int)partDay);
                nextTerm = c.getTime();
                customer.getLoan().getPartdays()[i] = nextTerm.getTime();
            }
            String msg = GetString.getLoanAMsg();
            System.out.println(msg);
            return ResultFactory.loanSuccess(msg);
        }
    }
}
