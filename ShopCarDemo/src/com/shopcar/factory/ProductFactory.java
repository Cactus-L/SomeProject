package com.shopcar.factory;

import com.shopcar.bean.Param;
import com.shopcar.bean.Product;


public class ProductFactory {
    public static Product productFactory(int id, String name, double price, int storage){
        Product product = new Product(id, name, price, storage);
        return product;
    }
    public static void secondProductFactory(Product product, String paramName, String picked){
        Double pickedPrice = product.getParameters().get(paramName).get(picked);
        product.getPickedParam().put(paramName, new Param(picked, pickedPrice));
        product.setPrice(product.getPrice() + pickedPrice);
    }
}
