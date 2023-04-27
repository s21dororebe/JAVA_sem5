package lv.venta.java_sem5.services.impl;

import lv.venta.java_sem5.model.Product;
import lv.venta.java_sem5.services.ICRUDProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CRUDProductServiceImpl implements ICRUDProductService {
    private final ArrayList<Product> allProducts = new ArrayList<>(List.of(new Product ("Apple", "delicious", 1.20f, 5), new Product ("Orange", "orange", 0.52f, 60), new Product("Banana", "yellow", 1.29f, 20)));

    @Override
    public void addNewProduct(String title, String description, float price, int quantity) throws Exception {
        //TODO verification with regex title and description
        if(title!=null && description!=null && price>0 && price<10000 && quantity>0 && quantity<1000000){
            boolean isFound = false;
            for(Product temp : allProducts){
                if(temp.getTitle().equals(title) && temp.getDescription().equals(description) && temp.getPrice() == price){
                    temp.setQuantity(temp.getQuantity() + quantity);
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                allProducts.add(new Product(title, description, price, quantity));
            }
        } else throw new Exception("Invalid input product data");
    }

    @Override
    public ArrayList<Product> retrieveAllProducts() {
        return allProducts;
    }

    @Override
    public Product retrieveById(long id) throws Exception {
        if(id > 0){
            for(Product temp : allProducts){
                if(temp.getId() == id){
                    return temp;
                }
            }
            throw new Exception("There is no product with this id");
        } else throw new Exception("Invalid input id");
    }

    @Override
    public void update(long id, String title, String description, float price, int quantity) throws Exception {

    }

    @Override
    public void deleteById(long id) throws Exception {

    }
}
