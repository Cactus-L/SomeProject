package com.shopcar.bean;

public class ReceiveMsg {
    private int rId;
    private String receiveName;
    private String phone;
    private String addr;
    private boolean userDefault; //是否为默认地址

    public ReceiveMsg(int rId, String receiveName, String phone, String addr) {
        this.rId = rId;
        this.receiveName = receiveName;
        this.phone = phone;
        this.addr = addr;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public boolean isUserDefault() {
        return userDefault;
    }

    public void setUserDefault(boolean userDefault) {
        this.userDefault = userDefault;
    }

    @Override
    public String toString() {
        return "ReceiveMsg{" +
                "rId=" + rId +
                ", receiveName='" + receiveName + '\'' +
                ", phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                ", userDefault=" + userDefault +
                '}';
    }
}
