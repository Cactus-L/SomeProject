package com.shopcar.services;

import com.shopcar.bean.PItem;
import com.shopcar.bean.ShopCar;

public class BuyService {
    //进行购买操作，将商品加入购物车
    public static void buy(ShopCar spCar, PItem item){
        switch (CheckStockService.checkStock(item).getResCode()){
            case 1:
                spCar.addItem(item);
                System.out.println("商品添加购物车成功！");
                break;
            case -1:
                System.out.println("商品库存不足无法购买！");
                break;
            case 0:
                System.out.println("发生未知购买错误！");
            default:
                System.out.println("BuyServices发生未知错误！");
        }
    }
}
