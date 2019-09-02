package com.bankdemo.run;

import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.Loan;
import com.bankdemo.services.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RunPart {
    public static void transferPart(Scanner sc) throws Exception{
        System.out.println("请输入您的转账账号，及转账金额");
        System.out.print("账号：");
        String aimId = sc.next();
        System.out.print("金额：");
        double money = Double.valueOf(sc.next());
        CustomerInfo.getInstance().getRecords().add(TransferService.transferAccounts(aimId, money, sc).toString());
    }
    public static void depositPart(Scanner sc){
        System.out.println("假装您把钱放到了ATM机里，请输入金额：");
        double money = Double.valueOf(sc.next());
        CustomerInfo.getInstance().getRecords().add(DepositService.deposit(money).toString());
    }
    public static void settingPart(Scanner sc){
        System.out.println("请选择您要修改的内容：");
        System.out.println("1. 密码       2.联系电话");
        int picked = sc.nextInt();
        switch (picked){
            case 1:
                System.out.println("请输入您的新密码：");
                String pwd = sc.next();
                CustomerInfo.getInstance().getRecords().add(SettingService.settingPwd(pwd).toString());
                break;
            case 2:
                System.out.println("请输入您的新联系方式：");
                String phone = sc.next();
                CustomerInfo.getInstance().getRecords().add(SettingService.settingPhone(phone).toString());
                break;
            default:
                System.out.println("输入错误！");
                break;
        }
    }
    public static void applyLoanPart(Scanner sc){
        CustomerInfo customer = CustomerInfo.getInstance();
        double applyLimit = CreditService.creditRating();
        if(customer.getCreditLevel().isBlank()){
            System.out.println("您是我行的黑名单用户，不可以贷款哦！");
        }else{
            System.out.println("您的信用额度为： " + applyLimit + " 元");
            System.out.println("请输入贷款数额：");
            double applyMoney = Double.parseDouble(sc.next());
            System.out.println("请输入贷款天数：");
            int day = sc.nextInt();
            System.out.println("请选择您的还款方式： 1.一期还清       2.分期还款");
            int pick = sc.nextInt();
            switch (pick){
                case 1:
                    customer.getRecords().add(ApplyLoanService.applyLoan1(applyMoney, applyLimit, day).toString());
                    break;
                case 2:
                    System.out.println("请输入还款期数（最多12期）：");
                    int term = sc.nextInt();
                    if(term <= 12){
                        customer.getRecords().add(ApplyLoanService.amortizationLoan(applyMoney, applyLimit, day, term).toString());
                    }else{
                        System.out.println("输入期数错误，贷款申请失败");
                    }
                    break;
                default:
                    System.out.println("还款方式选择错误，本次贷款申请失败！");
                    break;
            }

        }
    }
    public static void repaymentPart(Scanner sc){

        System.out.println("请选择您的还款方式：1. 分期还款   2.提前还款（分期） 3.一期还款");
        int flag = sc.nextInt();
        switch (flag){
            case 1:
                amortizationRepayPart(sc);
                break;
            case 2:
                earlyRepaymentPart(sc);
                break;
            case 3:
                Repayment1Part(sc);
                break;
            default:
                break;
        }

    }
    public static void amortizationRepayPart(Scanner sc){
        CustomerInfo customer = CustomerInfo.getInstance();
        if(customer.getLoan().getModel() > 1) {
            switch (RepaymentService.checkPartDay().getFlag()) {
                case 0:
                    System.out.println("您本期共需还款： " + customer.getLoan().getPartValue() + "元");
                    System.out.println("假装您把钱放入了ATM机，您放入的金额为：");
                    double money = Double.parseDouble(sc.next());
                    customer.getRecords().add(RepaymentService.amortizationRepay(money, 0, RepaymentService.checkPartDay().getTerm()).toString());
                    break;
                case 1:
                    System.out.println("您本期共需还款： " + (customer.getLoan().getPartValue() * 2) + "元");
                    System.out.println("假装您把钱放入了ATM机，您放入的金额为：");
                    double money1 = Double.parseDouble(sc.next());
                    customer.getRecords().add(RepaymentService.amortizationRepay(money1, 1, RepaymentService.checkPartDay().getTerm()).toString());
                    break;
                default:
                    System.out.println("由于您拖欠2期及以上贷款，取消分期贷款，改为一期还款模式，请重新选择还款模式");
                    System.out.println("您的信用等级为：" + customer.getCreditLevel().getLevel());
            }
        }else{
            System.out.println("您的贷款模式为：一期还清，请重新选择");
        }
    }
    public static void Repayment1Part(Scanner sc){
        CustomerInfo customer = CustomerInfo.getInstance();
        if(customer.getLoan().getModel() <= 1){
            System.out.println("您共需还款： " + (customer.getLoan().getRate()*customer.getLoan().getLoanValue() + customer.getLoan().getLoanValue()) + "元");
            System.out.println("假装您把钱放入了ATM机，您放入的金额为：");
            double money = Double.parseDouble(sc.next());
            customer.getRecords().add(RepaymentService.repayment1(money).toString());
        }else{
            System.out.println("您的贷款模式为：分期还款，请重新选择");
        }
    }
    public static void earlyRepaymentPart(Scanner sc){
        CustomerInfo customer = CustomerInfo.getInstance();
        Loan loan = customer.getLoan();
        Date now = new Date();
        long loanTime = now.getTime() - customer.getLoan().getStartDay();
        double loanDay = loanTime / (1000 * 60 * 60 * 24);// long相减得到贷款时间毫秒数，然后转为贷款天数
        //获得还款金额： （利率*贷款金额）* （目前贷款天数 / 贷款总天数） + 贷款金额
        double a = loan.getRate() * loan.getLoanValue();
        double b = loanDay / loan.getApplyDay();
        double sum = a * b + loan.getLoanValue();
        System.out.println("您共贷款 " + loan.getLoanValue() + "元，已贷款 " + loanDay + "天，目前需要还款 " + sum + " 元");
        System.out.println("(计算公式为： 还款金额 =（利率*贷款金额）* （目前贷款天数 / 贷款总天数） + 贷款金额)");
        System.out.println("假装您把钱放入了ATM机，您放入的金额为：");
        double money = Double.parseDouble(sc.next());
        customer.getRecords().add(EarlyRepaymentService.earlyRepayment(sum, money).toString());
    }
    public static void queryRepaymentPart(Scanner sc){
        QueryRepaymentService.queryRepayment();
    }
    public static void showUserInfo(){
       ShowUserInfoService.showUserInfo();
    }
    public static void voucher(){
        System.out.println("【本次操作凭单】");
        List<String> records = CustomerInfo.getInstance().getRecords();
        Iterator it = records.iterator();
        while (it.hasNext()){
            String result = (String) it.next();
            System.out.println("---------------------------------------------------------------------");
            System.out.println(result);
        }
    }
}
