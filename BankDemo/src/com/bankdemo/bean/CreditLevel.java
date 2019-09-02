package com.bankdemo.bean;

public class CreditLevel {
    private int level = 1; //信用等级
    private boolean blank = false; //是否为黑名单

    public CreditLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isBlank() {
        return blank;
    }

    public void setBlank(boolean blank) {
        this.blank = blank;
    }

    @Override
    public String toString() {
        return "CreditLevel{" +
                "level=" + level +
                ", blank=" + blank +
                '}';
    }
}
