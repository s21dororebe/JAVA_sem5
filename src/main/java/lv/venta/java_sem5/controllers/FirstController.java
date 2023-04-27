package lv.venta.java_sem5.controllers;

import lv.venta.java_sem5.model.Product;
import lv.venta.java_sem5.services.ICRUDProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FirstController {

    @Autowired
    private ICRUDProductService crudService;

    @GetMapping("/hello") //localhost:8080/hello
    public String getHello(){
        System.out.println("Hello World");
        return "hello-page"; //here is the page hello-page.html
    }

    @GetMapping("/msg") //localhost:8080/msg
    public String getMsg(Model model){
        model.addAttribute("packet", "Message from good girl");
        System.out.println("good girl");
        return "msg-page"; //msg-page.html
    }

    @GetMapping("/one-product") //localhost:8080/one-product
    public String getOnProduct(Model model){
        Product product = new Product("Apple", "delicious", 1.2f, 5); //1.2 double => 1.2f float
        model.addAttribute("packet", product);
        return "one-product-page";
    }

    @GetMapping("/list-of-products") //localhost:8080/list-of-products
    public String getListOfProducts(Model model){
        model.addAttribute("packet", crudService.retrieveAllProducts()); //I put a list of products into the box
        return "list-of-products-page"; //I return page to show user the data
    }

    @GetMapping("/list-of-products-find") //localhost:8080/list-of-products-find?id=2
    public String getProductByID(@RequestParam("id") long id, Model model) {
        try {
            Product prod = crudService.retrieveById(id);
            model.addAttribute("packet", prod);
            return "one-product-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/list-of-products/{id}") //localhost:8080/list-of-products/2
    public String getProductByPage(@PathVariable("id") long id, Model model){
        try {
            Product prod = crudService.retrieveById(id);
            model.addAttribute("packet", prod);
            return "one-product-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/add-product") //localhost:8080/add-product
    public String getAddProduct(Model model){
        model.addAttribute("product", new Product()); //send an empty product
        return "add-product-page";
    }
    @PostMapping("/add-product")
    public String postAddProduct(Product product) {
        try {
            crudService.addNewProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
            return "redirect:/list-of-products";
        } catch (Exception e){
            return "redirect:/error";
        }
    }

    @GetMapping("/update-product/{id}") //localhost:8080/update-product/1
    public String getUpdateProduct(@PathVariable("id") long id, Model model){
        try {
            model.addAttribute("product", crudService.retrieveById(id));
            return "update-product-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
    @PostMapping("/update-product/{id}")
    public String postUpdateProduct(@PathVariable("id") long id, Product product){
        try {
            crudService.update(id, product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
            return "redirect:/list-of-products/" + id;
        } catch (Exception e){
            return "redirect:/error";
        }
    }
    @GetMapping("/error") //localhost:8080/error
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }
    @GetMapping("/delete-product/{id}") //localhost:8080/delete-product/1
    public String getDeleteProduct(@PathVariable("id") long id, Model model){
        try {
            crudService.deleteById(id);
            model.addAttribute("product", crudService.retrieveAllProducts());
            return "all-product-page";
        } catch (Exception e){
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }
}
