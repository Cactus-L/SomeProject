package com.shopcar.bean;

public class Param {
    private String paramName;
    private Double price;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Param(String paramName, Double price) {
        this.paramName = paramName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Param{" +
                "paramName='" + paramName + '\'' +
                ", price=" + price +
                '}';
    }
}
