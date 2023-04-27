package lv.venta.java_sem5.services;

import lv.venta.java_sem5.model.Product;

import java.util.ArrayList;

public interface ICRUDProductService {

    //BY DEFAULT ALL FUNCTIONS ARE PUBLIC AND ABSTRACT
    //CRUD

    //C - create
    void addNewProduct(String title, String description, float price, int quantity) throws Exception;
     //R - retrieve all
    ArrayList<Product> retrieveAllProducts();
    //R - retrieve by id
    Product retrieveById(long id) throws Exception;
    //U - update
    void update(long id, String title, String description, float price, int quantity) throws Exception;
    //D - delete
    void deleteById(long id) throws Exception;
}
