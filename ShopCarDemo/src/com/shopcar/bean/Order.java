package com.shopcar.bean;

import java.util.Date;
import java.util.Iterator;

public class Order {
    private int oId;
    private ShopCar spCar;
    private Customer customer;
    private Long date; //订单生成时间
    {
        Date now = new Date();
        date = now.getTime();
    }

    public Order(int oId, ShopCar spCar, Customer customer) {
        this.oId = oId;
        this.spCar = spCar;
        this.customer = customer;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public ShopCar getSpCar() {
        return spCar;
    }

    public void setSpCar(ShopCar spCar) {
        this.spCar = spCar;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getDate() {
        return date;
    }

    public void showOrder(){
        System.out.println("订单信息表");
        System.out.println("-------------------------------------------------------------");
        System.out.println("订单ID: " + this.getoId());
        Date orderTime = new Date(this.getDate());
        System.out.println("订单生成时间： " + orderTime);
        System.out.println("订单内容:");
        Iterator it = this.getSpCar().getItems().iterator();
        while (it.hasNext()){
            PItem item = (PItem) it.next();
            System.out.println("商品ID: " + item.getProduct().getPid() + "  商品名: " + item.getProduct().getProductName() + "  商品单价: " + item.getProduct().getPrice() + "  商品数量: " + item.getNum());
        }
        System.out.println("商品总价： " + this.getSpCar().getTotal() + "元");
        System.out.println("顾客信息：");
        System.out.println("用户ID: " + this.getCustomer().getUserId());
        System.out.println("用户名: " + this.getCustomer().getUserName());
        System.out.println("用户收件信息：");
        ReceiveMsg receiveMsg = this.getCustomer().getDefaultReceiveMsg();
        System.out.println("收件人姓名：" + receiveMsg.getReceiveName());
        System.out.println("收件人联系方式：" + receiveMsg.getPhone());
        System.out.println("收件人地址：" + receiveMsg.getAddr() );
        System.out.println("-------------------------------------------------------------");
    }
}
