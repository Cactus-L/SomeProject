package com.shopcar.builder;

import com.shopcar.bean.BuyResult;
import com.shopcar.bean.PItem;
import com.shopcar.enumeration.BuyResultEnum;

public class BuyResultBuilder {
    //BuyResult操作类，用于快速得到某种固定的BuyResult对象
    //放回成功的BuyResult对象
    public static BuyResult success(PItem item){
        BuyResult buyResult = new BuyResult(BuyResultEnum.SUCCESS.getRescode(), BuyResultEnum.SUCCESS.getResMsg());
        buyResult.setItem(item);
        return  buyResult;
    }
    //返回其他的BuyResult对象
    public static BuyResult error(int code, String msg){
        BuyResult buyResult = new BuyResult(code, msg);
        return buyResult;
    }
}
