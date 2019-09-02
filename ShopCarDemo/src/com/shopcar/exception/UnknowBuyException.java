package com.shopcar.exception;

import com.shopcar.enumeration.BuyResultEnum;

public class UnknowBuyException extends Exception{
    //未知购买异常
    private int resCode;
    public UnknowBuyException(){
        super(BuyResultEnum.UNKNOW.getResMsg());
        this.resCode = BuyResultEnum.UNKNOW.getRescode();
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }
}
