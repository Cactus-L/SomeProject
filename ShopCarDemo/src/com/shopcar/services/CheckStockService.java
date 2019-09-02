package com.shopcar.services;

import com.shopcar.bean.BuyResult;
import com.shopcar.bean.PItem;
import com.shopcar.bean.ShopCar;
import com.shopcar.builder.BuyResultBuilder;
import com.shopcar.enumeration.BuyResultEnum;
import com.shopcar.exception.NotEnoughException;
import com.shopcar.exception.UnknowBuyException;

public class CheckStockService {
    //检查库存是否满足购买数量
    public static BuyResult checkStock(PItem item){
        try {
            if(item.getProduct().getStorage() >= item.getNum()){
                return BuyResultBuilder.success(item);
            }else if(item.getProduct().getStorage() < item.getNum()){
                throw new NotEnoughException();
            }else{
                throw new UnknowBuyException();
            }
        }catch (NotEnoughException e){
            return new BuyResult(e.getResCode(), e.getMessage());
        }catch (UnknowBuyException e){
            return new BuyResult(e.getResCode(), e.getMessage());
        }
    }
}
