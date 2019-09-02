package com.bankdemo.bean;

public class Balance {
    private double money;//当前余额
    private double addMoney;//本次操作所添加的金额
    private double reduceMoney; //本次操作所减少的金额
    int modify = 0;//计算别人给自己的转账次数

    public Balance(double money, double addMoney, double reduceMoney, int modify) {
        this.money = money;
        this.addMoney = addMoney;
        this.reduceMoney = reduceMoney;
        this.modify = modify;
    }

    public double getMoney() {
        return money;
    }

    public double getAddMoney() {
        return addMoney;
    }

    public void setAddMoney(double addMoney) {
        this.addMoney = addMoney;
    }

    public double getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(double reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getModify() {
        return modify;
    }

    public void setModify(int modify) {
        this.modify = modify;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "money=" + money +
                ", addMoney=" + addMoney +
                ", reduceMoney=" + reduceMoney +
                ", modify=" + modify +
                '}';
    }
}
