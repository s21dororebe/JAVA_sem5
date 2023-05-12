package lv.venta.java_sem5.services.impl;

import lv.venta.java_sem5.model.Product;
import lv.venta.java_sem5.repos.IProductRepo;
import lv.venta.java_sem5.services.ICRUDProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CRUDProductServiceImpl implements ICRUDProductService {
    @Autowired
    private IProductRepo productRepo;

    @Override
    public void addNewProduct(String title, String description, float price, int quantity) throws Exception {
        boolean isFound = false;
        if(productRepo.existsByTitleAndDescriptionAndPrice(title, description, price)){
            Product temp = productRepo.findByTitleAndDescriptionAndPrice(title, description, price);
            temp.setQuantity(temp.getQuantity() + quantity);
            isFound = true;
        }
        if(!isFound){
            productRepo.save(new Product(title, description, price, quantity));
        }
    }

    @Override
    public ArrayList<Product> retrieveAllProducts() {
        return (ArrayList<Product>) productRepo.findAll();
    }

    @Override
    public Product retrieveById(long id) throws Exception {
        if(id > 0){
            if(productRepo.existsById(id)){
                return productRepo.findById(id).get();
            } else throw new Exception("There is no product with this id");
        } else throw new Exception("Invalid input id");
    }

    @Override
    public void update(long id, String title, String description, float price, int quantity) throws Exception {
        if(id > 0){
            if(productRepo.existsById(id)) {
                Product temp = productRepo.findById(id).get();
                temp.setTitle(title);
                temp.setDescription(description);
                temp.setPrice(price);
                temp.setQuantity(quantity);
                productRepo.save(temp);
            } else throw new Exception("Product not found");
        } else throw new Exception("Invalid product id");
    }

    @Override
    public void deleteById(long id) throws Exception {
        if(productRepo.existsById(id))
            productRepo.deleteById(id);
        else throw new Exception("There is no product with this id");
    }
}
