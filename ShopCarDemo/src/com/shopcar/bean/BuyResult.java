package com.shopcar.bean;

public class BuyResult {
    private int resCode;//购买状态码 -1：库存不足 0：未知错误 1：购买成功
    private String resMsg; //购买状态信息
    private PItem item; //所购商品及其数量

    public BuyResult() {
    }
    public BuyResult(int resCode, String resMsg) {
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public PItem getItem() {
        return item;
    }

    public void setItem(PItem item) {
        this.item = item;
    }
}
