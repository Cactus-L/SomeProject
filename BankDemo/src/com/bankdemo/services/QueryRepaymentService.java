package com.bankdemo.services;

import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.Loan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryRepaymentService {
    public static void queryRepayment(){
        CustomerInfo customer = CustomerInfo.getInstance();
        Loan loan = customer.getLoan();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        System.out.println("用户姓名：" + customer.getUserName());
        System.out.println("信用额度：" + loan.getLoanLimit() + "元");
        System.out.println("贷款金额：" + loan.getLoanValue() + "元");
        System.out.println("贷款利率：" + loan.getRate());
        System.out.println("贷款开始时间：" + sdf.format(new Date(loan.getStartDay())));
        if(loan.getModel() <= 1){
            System.out.println("还款模式：一期还清");
            System.out.println("最迟还款时间：" + sdf.format(new Date(loan.getBackTime())));
        }else{
            System.out.println("分期还款金额：" + loan.getPartValue() + "元");
            System.out.println("还款模式：分期还款");
            System.out.println("最迟还款时间：" + sdf.format(new Date(loan.getBackTime())));
            System.out.println("每期还款时间：");
            long[] partDays = loan.getPartdays();
            for (int i = 0; i < loan.getModel(); i++){
                System.out.println("第" + (i+1) + "期最迟还款时间：" + sdf.format(partDays[i]));
            }
        }
    }
}
