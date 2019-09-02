package com.shopcar.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {
    private int pid;
    private String productName;
    private double price;
    private int storage;
    Map<String, Map<String, Double>> parameters = new HashMap<String, Map<String, Double>>(); //商品参数列表
    Map<String, Param> pickedParam = new HashMap<String, Param>(); //已选商品参数

    public Map<String, Param> getPickedParam() {
        return pickedParam;
    }

    public void setPickedParam(Map<String, Param> pickedParam) {
        this.pickedParam = pickedParam;
    }

    public Product(){

    }
    public Product(int pid, String productName, double price, int storage) {
        this.pid = pid;
        this.productName = productName;
        this.price = price;
        this.storage = storage;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<String, Map<String, Double>> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Map<String, Double>> parameters) {
        this.parameters = parameters;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", storage=" + storage +
                ", parameters=" + parameters +
                ", pickedParam=" + pickedParam +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getPid() == product.getPid();
    }
}
