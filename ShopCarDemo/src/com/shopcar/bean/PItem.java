package com.shopcar.bean;

import java.util.Objects;

public class PItem {
    private Product product;
    private int num;

    public PItem() {
    }

    public PItem(Product product, int num) {
        this.product = product;
        this.num = num;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PItem{" +
                "product=" + product +
                ", num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PItem)) return false;
        PItem pItem = (PItem) o;
        return Objects.equals(getProduct(), pItem.getProduct());
    }
}
