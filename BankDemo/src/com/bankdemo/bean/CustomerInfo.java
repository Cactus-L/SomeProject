package com.bankdemo.bean;

import java.util.List;

public class CustomerInfo extends UserInfo {
    private static CustomerInfo customerInfo = new CustomerInfo();

    private CustomerInfo() {
    }

    public static CustomerInfo getInstance(){
        return customerInfo;
    }
    public void showInfo() {
        System.out.println("银行账号：" + customerInfo.getUserId() + "\n"
                + "密码：" + customerInfo.getPassword() + "\n"
                + "用户姓名：" + customerInfo.getUserName() + "\n"
                + "联系方式：" + customerInfo.getPhone() + "\n"
                + "账户余额：" + customerInfo.getBalance().getMoney() + "元\n"
                + "信用等级：" + customerInfo.getCreditLevel().getLevel() + "\n"
                + "信用额度：" + customerInfo.getLoan().getLoanLimit() + "元\n"
                + "已贷金额：" + customerInfo.getLoan().getLoanValue() + "元\n"
                + "操作记录：" + "\n"
        );
        List<String> recodes = CustomerInfo.getInstance().getRecords();
        for (String s : recodes){
            System.out.println(s);
        }
    }
    public static void initCustomer(UserInfo u) throws Exception{
        customerInfo.setUserId(u.getUserId());
        customerInfo.setPassword(u.getPassword());
        customerInfo.setUserName(u.getUserName());
        customerInfo.setPhone(u.getPhone());
        customerInfo.setBalance(u.getBalance());
        customerInfo.setCreditLevel(u.getCreditLevel());
        customerInfo.setLoan(u.getLoan());
        customerInfo.setRecords(u.getRecords());
    }
    public static void cover() throws Exception{
        UserInfo user = AllInfo.getUserInfos().get(AllInfo.getUserInfos().indexOf(customerInfo));
        user.setUserId(customerInfo.getUserId());
        user.setPassword(customerInfo.getPassword());
        user.setUserName(customerInfo.getUserName());
        user.setPhone(customerInfo.getPhone());
        user.setBalance(customerInfo.getBalance());
        user.setCreditLevel(customerInfo.getCreditLevel());
        user.setLoan(customerInfo.getLoan());
        user.setRecords(customerInfo.getRecords());
    }
}
