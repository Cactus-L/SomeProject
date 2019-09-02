package com.shopcar.services;

import com.shopcar.bean.PItem;
import com.shopcar.bean.ShopCar;

public class RemoveService {
    //将商品从购物车移出或者修改数量
    public static void remove(ShopCar spCar, PItem item){
        if(spCar.removeItem(item)){
            System.out.println("商品移出成功！");
        }else{
            System.out.println("商品移出失败！");
        }
    }
}
