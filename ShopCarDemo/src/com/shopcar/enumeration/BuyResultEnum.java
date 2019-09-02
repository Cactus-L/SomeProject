package com.shopcar.enumeration;

public enum BuyResultEnum {
    SUCCESS(1,"success"),
    UNKNOW(0,"unknow error"),
    NOT_ENOUGH(-1,"stock is not enough");
    private int rescode;
    private String resMsg;

    BuyResultEnum(int rescode, String resMsg) {
        this.rescode = rescode;
        this.resMsg = resMsg;
    }

    public int getRescode() {
        return rescode;
    }

    public void setRescode(int rescode) {
        this.rescode = rescode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
