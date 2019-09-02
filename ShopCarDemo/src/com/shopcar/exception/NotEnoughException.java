package com.shopcar.exception;

import com.shopcar.enumeration.BuyResultEnum;

public class NotEnoughException extends Exception{
    //库存不够异常
    private int resCode;
    public NotEnoughException(){
        super(BuyResultEnum.NOT_ENOUGH.getResMsg());
        this.resCode = BuyResultEnum.NOT_ENOUGH.getRescode();
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }
}
