package com.bankdemo.services;

import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetString {
    public static String getLoanMsg(){
        CustomerInfo customer = CustomerInfo.getInstance();
        double sum = (customer.getLoan().getRate() * customer.getLoan().getLoanValue()) + customer.getLoan().getLoanValue();
        StringBuffer sb = new StringBuffer();
        sb.append("贷款业务\n");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        sb.append("贷款数额:").append(customer.getLoan().getLoanValue()).append("元\r\n");
        sb.append("账户余额:").append(customer.getBalance().getMoney()).append("元\r\n");
        sb.append("贷款利率:").append(customer.getLoan().getRate()).append("\r\n");
        sb.append("贷款开始时间:").append(sdf.format(new Date(customer.getLoan().getStartDay()))).append("\r\n");
        sb.append("贷款时长:").append(customer.getLoan().getApplyDay()).append("天\r\n");
        sb.append("最迟还款时间:").append(sdf.format(new Date(customer.getLoan().getBackTime()))).append("\r\n");
        sb.append("需还款金额:").append(sum).append("元\r\n");
        sb.append("业务操作时间：").append(sdf.format(customer.getLoan().getStartDay())).append("\n");
        return sb.toString();
    }
    public static String getLoanAMsg(){
        CustomerInfo customer = CustomerInfo.getInstance();
        double sum = (customer.getLoan().getRate() * customer.getLoan().getLoanValue()) + customer.getLoan().getLoanValue();
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        sb.append(GetString.getLoanMsg());
        sb.append("每期还款金额:").append(customer.getLoan().getPartValue()).append("元\r\n");
        int i = 0;
        long[] partDays = customer.getLoan().getPartdays();
        while (partDays[i] > 0){
            sb.append("第").append(i+1).append("期最迟还款时间：").append(sdf.format(partDays[i])).append("\r\n");
            i++;
        }
        sb.append("声明：\r\n");
        sb.append("分期还款每一期都需要顾客还款，若其中某一期无法还清，则会将其金额顺延至下一期。（下一期要还两期的贷款金额）\r\n");
        sb.append("若下一期仍未完成还款，则会取消您的分期还款模式，改为一期还清模式。\r\n");
        sb.append("业务操作时间：").append(sdf.format(customer.getLoan().getStartDay())).append("\n");
        return sb.toString();
    }
    public static String getTransferMsg(UserInfo u){
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date now = new Date();
        sb.append("转账业务\n");
        sb.append("目标账户： ").append(u.getUserId()).append("\n");
        sb.append("转账金额： ").append(u.getBalance().getAddMoney()).append("\n");
        sb.append("业务操作时间：").append(sdf.format(now.getTime())).append("\n");
        return sb.toString();
    }
    public static String getDepositMsg(){
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date now = new Date();
        sb.append("存款业务\n");
        sb.append("目标账户： ").append(CustomerInfo.getInstance().getUserId()).append("\n");
        sb.append("存入金额： ").append(CustomerInfo.getInstance().getBalance().getAddMoney()).append("\n");
        sb.append("业务操作时间：").append(sdf.format(now.getTime())).append("\n");
        return sb.toString();
    }
    public static String getSettingMsg(){
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date now = new Date();
        sb.append("修改个人信息业务\n");
        sb.append("目标账户： ").append(CustomerInfo.getInstance().getUserId()).append("\n");
        sb.append("用户名： ").append(CustomerInfo.getInstance().getUserName()).append("\n");
        sb.append("账户密码： ").append(CustomerInfo.getInstance().getPassword()).append("\n");
        sb.append("联系方式：").append(CustomerInfo.getInstance().getPhone()).append("\n");
        sb.append("业务操作时间：").append(sdf.format(now.getTime())).append("\n");
        return sb.toString();
    }
    public static String getAmortizationRepaygMsg(int term, double money, double sum2){
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date now = new Date();
        sb.append("还款业务\n");
        sb.append("第").append(term+1).append("期还款\n");
        sb.append("还款金额： ").append(money).append("元\n");
        sb.append("应还金额：").append(sum2).append("元\n");
        sb.append("账户余额：").append(CustomerInfo.getInstance().getBalance().getMoney()).append("元\n");
        sb.append("还款状态：成功\n");
        sb.append("业务操作时间：").append(sdf.format(now.getTime())).append("\n");
        return sb.toString();
    }
    public static String getRepayment1Msg(double money, double sum){
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date now = new Date();
        sb.append("还款业务\n");
        sb.append("还款金额： ").append(money).append("元\n");
        sb.append("应还金额：").append(sum).append("元\n");
        sb.append("账户余额：").append(CustomerInfo.getInstance().getBalance().getMoney()).append("元\n");
        sb.append("还款状态：成功\n");
        sb.append("业务操作时间：").append(sdf.format(now.getTime())).append("\n");
        return sb.toString();
    }
}
