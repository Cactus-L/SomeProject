package com.bankdemo.bean;

public class TermCheck {
    private int term;//当前还款期数
    private int flag;//分期还款模式下：0无拖欠，1拖欠1期，2-12拖欠n期

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public TermCheck(int term, int flag) {
        this.term = term;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "TermCheck{" +
                "term=" + term +
                ", flag=" + flag +
                '}';
    }
}
