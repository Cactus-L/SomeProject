package com.bankdemo.services;

import com.bankdemo.factory.ResultFactory;
import com.bankdemo.bean.*;
import com.bankdemo.bean.Result;
import com.bankdemo.bean.ResultEnum;
import com.bankdemo.bean.TermCheck;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RepaymentService {
    public static Result repayment1(double money){
        CustomerInfo customer = CustomerInfo.getInstance();
        Loan loan = customer.getLoan();
        Balance balance = customer.getBalance();
        double sum = loan.getRate()*loan.getLoanValue() + loan.getLoanValue();
        Date now = new Date();
        if(now.getTime() <= loan.getBackTime()){
            if(money >= sum){
                customer.getBalance().setAddMoney(money - sum);
                customer.getBalance().setMoney(balance.getMoney() + money - sum);
                customer.getCreditLevel().setLevel(customer.getCreditLevel().getLevel() + 1);
                customer.setLoan(new Loan(loan.getLoanLimit(),0,0,0,0,0));
                System.out.println("提前还款成功，您的余额增加 " + (money - sum) + " 元,账户余额为：" + customer.getBalance().getMoney() + "元");
                System.out.println("您的信用额度为： " + customer.getCreditLevel().getLevel());
                String msg = GetString.getRepayment1Msg(money, sum);
                System.out.println(msg);
                return ResultFactory.repaymentSuccess(msg);
            }else{
                if(balance.getMoney() >= (sum - money)){
                    customer.setLoan(new Loan(loan.getLoanLimit(),0,0,0,0,0));
                    customer.getBalance().setReduceMoney(sum - money);
                    customer.getBalance().setMoney(balance.getMoney() - (sum - money));
                    System.out.println("提前还款成功，您的余额减少" + (sum - money) + " 元,账户余额为：" + customer.getBalance().getMoney() + "元");
                    String msg = GetString.getRepayment1Msg(money, sum);
                    System.out.println(msg);
                    return ResultFactory.repaymentSuccess(msg);
                }else{
                    System.out.println("提前还款失败，余额不足，还需要 " + (sum - money) + "元");
                    System.out.println("请努力赚钱，然后还我们钱吧！");
                    return ResultFactory.resultFactory(ResultEnum.MoneyNotEnough.getResCode(),ResultEnum.MoneyNotEnough.getMsg());
                }
            }
        }else{
            if(balance.getMoney() >= (sum - money)){
                customer.getBalance().setReduceMoney(sum - money);
                customer.getBalance().setMoney(balance.getMoney() - (sum - money));
                customer.getCreditLevel().setLevel(customer.getCreditLevel().getLevel() - 1);
                customer.setLoan(new Loan(loan.getLoanLimit(),0,0,0,0,0));
                System.out.println("还款超时，已自动扣取余额还款，您的余额减少" + (sum - money) + " 元,账户余额为：" + customer.getBalance().getMoney() + "元");
                System.out.println("您的信用额度为： " + customer.getCreditLevel().getLevel());
                String msg = GetString.getRepayment1Msg(money, sum);
                System.out.println(msg);
                return ResultFactory.repaymentSuccess(msg);
            }else{
                customer.setLoan(new Loan(0,sum - money,0,0,0,0));
                customer.getCreditLevel().setBlank(true);
                customer.getCreditLevel().setLevel(0);
                System.out.println("您未能按期还款，还需要 " + (sum - money) + "元");
                System.out.println("您已加入我行黑名单用户，您目前的信用等级为: 0");
                System.out.println("等法院传单吧！（资产抵押）");
                return ResultFactory.resultFactory(ResultEnum.MoneyNotEnough.getResCode(),ResultEnum.MoneyNotEnough.getMsg());
            }
        }
    }
    public static TermCheck checkPartDay(){
        Date now = new Date();
        CustomerInfo customer = CustomerInfo.getInstance();
        Loan loan = customer.getLoan();
        long[] partDays = loan.getPartdays();
        int i = 0, flag = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        System.out.println();
        while (partDays[i] < now.getTime()){
            if(partDays[i] != -1){
                flag++;
                i++;
            }else{
                i++;
            }
        }
        switch (flag){
            case 0:
                break;
            case 1:
                break;
            default:
                customer.getLoan().setModel(1);
                customer.getLoan().setPartdays(new long[12]);
                customer.getCreditLevel().setLevel(customer.getCreditLevel().getLevel() - 1);
                break;
        }
        // 0:代表无拖欠 1：拖欠一期 default:拖欠2期以上
        return new TermCheck(i, flag);
    }
    public static Result amortizationRepay(double money, int flag, int term){
        CustomerInfo customer = CustomerInfo.getInstance();
        Loan loan = customer.getLoan();
        Balance balance = customer.getBalance();
        double sum = loan.getPartValue();
        double sum2 = loan.getPartValue()*2;
        switch (flag){
            case 0:
                if(money >= sum){
                    customer.getLoan().getPartdays()[term] = -1;
                    customer.getLoan().setLoanValue(loan.getLoanValue() - sum);
                    customer.getBalance().setAddMoney(money - sum);
                    customer.getBalance().setMoney(balance.getMoney() + money - sum);
                    System.out.println("还款成功，您的余额增加" + (money - sum) + " 元,账户余额为：" + customer.getBalance().getMoney() + "元");
                    String msg = GetString.getAmortizationRepaygMsg(term, money, sum);
                    System.out.println(msg);
                    return ResultFactory.repaymentSuccess(msg);
                }else{
                    if(balance.getMoney() >= (sum - money)){
                        customer.getLoan().getPartdays()[term] = -1;
                        customer.getLoan().setLoanValue(loan.getLoanValue() - sum);
                        customer.getBalance().setReduceMoney(sum - money);
                        customer.getBalance().setMoney(balance.getMoney() - (sum - money));
                        System.out.println("还款成功，您的余额减少" + ( sum - money) + " 元,账户余额为：" + customer.getBalance().getMoney() + "元");
                        String msg = GetString.getAmortizationRepaygMsg(term, money, sum);
                        System.out.println(msg);
                        return ResultFactory.repaymentSuccess(msg);
                    }else{
                        System.out.println("还款金额不足，您未能按期还款，本次的金额会返还给您的账户，请在下一期同时还款");
                        return ResultFactory.resultFactory(ResultEnum.MoneyNotEnough.getResCode(),ResultEnum.MoneyNotEnough.getMsg());
                    }
                }
            case 1:
                if(money >= sum2){
                    customer.getLoan().getPartdays()[term] = -1;
                    customer.getLoan().getPartdays()[term - 1] = -1;
                    customer.getLoan().setLoanValue(loan.getLoanValue() - sum2);
                    customer.getBalance().setAddMoney(money - sum2);
                    customer.getBalance().setMoney(balance.getMoney() + money - sum2);
                    System.out.println("延期还款成功，您的余额增加 " + (money - sum2) + " 元,账户余额为：" + customer.getBalance().getMoney() + "元");
                    String msg = GetString.getAmortizationRepaygMsg(term, money, sum);
                    System.out.println(msg);
                    return ResultFactory.repaymentSuccess(msg);
                }else {
                    if (balance.getMoney() >= (sum2 - money)) {
                        customer.getLoan().getPartdays()[term] = -1;
                        customer.getLoan().getPartdays()[term - 1] = -1;
                        customer.getLoan().setLoanValue(loan.getLoanValue() - sum2);
                        customer.getBalance().setReduceMoney(sum2 - money);
                        customer.getBalance().setMoney(balance.getMoney() - (sum2 - money));
                        System.out.println("延期还款成功，您的余额减少" + (sum2 - money) + " 元,账户余额为：" + customer.getBalance().getMoney() + "元");
                        String msg = GetString.getAmortizationRepaygMsg(term, money, sum2);
                        System.out.println(msg);
                        return ResultFactory.repaymentSuccess(msg);
                    } else {
                        customer.getCreditLevel().setLevel(customer.getCreditLevel().getLevel() - 1);
                        System.out.println("您未能按期还款，本次的金额会返还给您的账户，您已由分期还款模式转为一期还清模式。请及时还款！");
                        System.out.println("您目前的信用等级为: " + customer.getCreditLevel().getLevel());
                        return ResultFactory.resultFactory(ResultEnum.MoneyNotEnough.getResCode(),ResultEnum.MoneyNotEnough.getMsg());
                    }
                }
            default:
                return ResultFactory.resultFactory(0,"无效操作");
        }
    }
}
