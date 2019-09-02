package com.shopcar.test;

import com.shopcar.bean.*;
import com.shopcar.factory.ProductFactory;
import com.shopcar.services.BuyService;
import com.shopcar.services.RemoveService;

import java.util.*;

public class BeanTest {
    public static void main(String[] args){
//        初始化商品信息
        List<Product> goodList = new ArrayList<Product>();
        Product p1 = ProductFactory.productFactory(1,"p1",100, 10);
        Product p2 = ProductFactory.productFactory(2,"p2",200, 10);
        Product p3 = ProductFactory.productFactory(3,"p3",300, 10);
        //初始化商品参数
//        设置p1的参数
        Map<String, Map<String, Double>> param1 = new HashMap<String,  Map<String, Double>>();
        ParamList colorValues = new ParamList(new Param("red",20.0), new Param("blue",20.0), new Param("yellow",20.0));
        ParamList storageValues = new ParamList(new Param("16G", 10.0), new Param("32G", 20.0), new Param("64G", 30.0));
        param1.put("color", colorValues.getParamList());
        param1.put("storage", storageValues.getParamList());
        p1.setParameters(param1);
//        设置p2的参数
        Map<String, Map<String, Double>> param2 = new HashMap<String, Map<String, Double>>();
        ParamList sizeValues = new ParamList(new Param("16cm",20.0), new Param("24cm",40.0));
        ParamList partsValues = new ParamList(new Param("1",20.0), new Param("2",40.0), new Param("3",40.0), new Param("4",40.0) );
        param2.put("size", sizeValues.getParamList());
        param2.put("parts", partsValues.getParamList());
        p2.setParameters(param2);
        goodList.add(p1);
        goodList.add(p2);
        goodList.add(p3);
        System.out.println("初始化商品列表：");
        Iterator it = goodList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");

//        商品加入购物车
        ShopCar spCar = new ShopCar();
        PItem i1 = new PItem(p1,1);
        ProductFactory.secondProductFactory(i1.getProduct(),"color", "red");
        ProductFactory.secondProductFactory(i1.getProduct(),"storage", "32G");
        PItem i2 = new PItem(p2,2);
        ProductFactory.secondProductFactory(i2.getProduct(),"size", "24cm");
        ProductFactory.secondProductFactory(i2.getProduct(),"parts", "2");
        PItem i3 = new PItem(p3,10);
        BuyService.buy(spCar,i1);
        BuyService.buy(spCar,i2);
        BuyService.buy(spCar,i3);
        spCar.showCar();
        System.out.println("--------------------------------------------------------------------------------------------------------");

//        商品移出购物车
        PItem i4 = new PItem(p3,2);
        PItem i5 = new PItem(p1,1);
        RemoveService.remove(spCar,i4);
        RemoveService.remove(spCar,i5);
        spCar.showCar();
        System.out.println("--------------------------------------------------------------------------------------------------------");

//        清空购物车
//        System.out.println("清空购物车");
//        spCar.clear();
//        spCar.showCar();
//        System.out.println("--------------------------------------------------------------------------------------------------------");

//        生成用户信息
        ReceiveMsg rm1 = new ReceiveMsg(1,"a","123","山西大学");
        ReceiveMsg rm2 = new ReceiveMsg(2,"b","123","天津东软");
        rm2.setUserDefault(true);
        List<ReceiveMsg> receiveMsgs = new ArrayList<ReceiveMsg>();
        receiveMsgs.add(rm1);
        receiveMsgs.add(rm2);
        Customer user = new Customer(1,"高高");
        user.setReceiveMsgs(receiveMsgs);
        System.out.println("--------------------------------------------------------------------------------------------------------");

//        生成订单信息
        Order order = new Order(1, spCar, user);
        order.showOrder();
    }
}
