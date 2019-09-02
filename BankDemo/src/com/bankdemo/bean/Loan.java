package com.bankdemo.bean;

import java.util.Arrays;

public class Loan {
    private double loanLimit;//贷款上限
    private  double loanValue; //当前贷款金额
    private  double partValue; //分期还款金额
    private double rate; //贷款计算利率
    private long startDay; //开始贷款时间
    private long backTime; //贷款归还时间
    private int applyDay; //贷款天数
    private int model; //还款方式: 1-12分别代表1-12期还款
    private long[] partdays = new long[12];

    public Loan(double loanValue, long startDay) {
        this.loanValue = loanValue;
        this.startDay = startDay;
    }

    public Loan(double loanLimit, double loanValue, double rate, long startDay, long backTime, int applyDay) {
        this.loanLimit = loanLimit;
        this.loanValue = loanValue;
        this.rate = rate;
        this.startDay = startDay;
        this.backTime = backTime;
        this.applyDay = applyDay;
    }

    public int getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(int applyDay) {
        this.applyDay = applyDay;
    }

    public long getBackTime() {
        return backTime;
    }

    public void setBackTime(long backTime) {
        this.backTime = backTime;
    }

    public double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(double loanLimit) {
        this.loanLimit = loanLimit;
    }

    public double getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(double loanValue) {
        this.loanValue = loanValue;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getStartDay() {
        return startDay;
    }

    public void setStartDay(long startDay) {
        this.startDay = startDay;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public long[] getPartdays() {
        return partdays;
    }

    public void setPartdays(long[] partdays) {
        this.partdays = partdays;
    }

    public double getPartValue() {
        return partValue;
    }

    public void setPartValue(double partValue) {
        this.partValue = partValue;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanLimit=" + loanLimit +
                ", loanValue=" + loanValue +
                ", partValue=" + partValue +
                ", rate=" + rate +
                ", startDay=" + startDay +
                ", backTime=" + backTime +
                ", applyDay=" + applyDay +
                ", model=" + model +
                ", partdays=" + Arrays.toString(partdays) +
                '}';
    }
}
