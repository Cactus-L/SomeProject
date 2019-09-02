package com.bankdemo.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserInfo {
    private String userId;
    private String password;
    private String phone;
    private String userName;
    private Balance balance = new Balance(0,0,0,0); //余额
    private CreditLevel creditLevel = new CreditLevel(1); //信用等级
    private Loan loan = new Loan(0,0); //贷款信息
    List<String> records = new ArrayList<String>();

    public UserInfo() {
    }
    public UserInfo(String userId) {
        this.userId = userId;
    }

    public UserInfo(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserInfo(String userId, String password, String phone, String userName, Balance balance) {
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.userName = userName;
        this.balance = balance;
    }

    public UserInfo(String userId, String password, String phone, String userName, Balance balance, CreditLevel creditLevel, Loan loan) {
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.userName = userName;
        this.balance = balance;
        this.creditLevel = creditLevel;
        this.loan = loan;
    }

    public List<String> getRecords() {
        return records;
    }

    public void setRecords(List<String> records) {
        this.records = records;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public CreditLevel getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(CreditLevel creditLevel) {
        this.creditLevel = creditLevel;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", creditLevel=" + creditLevel +
                ", loan=" + loan +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(getUserId(), userInfo.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
