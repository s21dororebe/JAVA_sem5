package lv.venta.java_sem5.services;

import lv.venta.java_sem5.model.Product;

import java.util.ArrayList;

public interface IFilteringProduct {
    //filter product: price less than x
    ArrayList<Product> filterByPriceLessThan(float priceThreshold) throws Exception;

    //filter product: quantity less than x
    ArrayList<Product> filterByQuantityLess(int quantityThreshold) throws Exception;

    //filter product: sorting
    //TODO asc or desc
    ArrayList<Product> sort() throws Exception;
}
