package com.shopcar.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShopCar {
    List<PItem> items = new ArrayList<PItem>(); //商品列表

    //购物车添加商品
    public boolean addItem(PItem pItem){
        if(this.items.contains(pItem)){
            PItem thisItem = this.items.get(this.items.indexOf(pItem));
            thisItem.setNum(thisItem.getNum() + pItem.getNum());
            return true;
        }else{
            return this.items.add(pItem);
        }
    }
    //购物车减少商品数量，以及移除商品
    public boolean removeItem(PItem pItem){
        if(this.items.contains(pItem)){
            PItem thisItem = this.items.get(this.items.indexOf(pItem));
            if(thisItem.getNum() == pItem.getNum()){
                return this.items.remove(pItem);
            }else{
                thisItem.setNum(thisItem.getNum() - pItem.getNum());
                return true;
            }
        }else{
            System.out.println("购物车不包含此商品");
            return true;
        }
    }
    //清空购物车
    public void clear(){
        this.items.clear();
    }
    //展示购物车内容
    public void showCar(){
        if(this.items.size() > 0){
            System.out.println("购物车清单如下：");
            Iterator it = this.items.iterator();
            while (it.hasNext()){
                PItem item = (PItem) it.next();
                System.out.println(item);
            }
        }else{
            System.out.println("购物车空空如也！");
        }
    }

    public double getTotal(){
        double total = 0;
        Iterator it = this.items.iterator();
        while (it.hasNext()){
            PItem item = (PItem) it.next();
            total += item.getNum() * item.getProduct().getPrice();
        }
        return total;
    }
    public List<PItem> getItems() {
        return items;
    }

    public void setItems(List<PItem> items) {
        this.items = items;
    }
}
