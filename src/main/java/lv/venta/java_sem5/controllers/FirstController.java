package lv.venta.java_sem5.controllers;

import lv.venta.java_sem5.model.Product;
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

    private final ArrayList<Product> listOfProducts = new ArrayList<>(List.of(new Product ("Apple", "delicious", 1.20f, 5), new Product ("Orange", "orange", 0.52f, 60), new Product("Banana", "yellow", 1.29f, 20)));


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
        model.addAttribute("packet", listOfProducts); //I put a list of products into the box
        return "list-of-products-page"; //I return page to show user the data
    }

    @GetMapping("/list-of-products-find") //localhost:8080/list-of-products-find?id=2
    public String getProductByID(@RequestParam("id") long id, Model model){
        if(id > 0) {
            for(Product temp : listOfProducts){
                if(temp.getId() == id){
                    model.addAttribute("packet", temp);
                    return "one-product-page";
                }
            }
        }
        model.addAttribute("packetError", "Wrong id");
        return "error-page";
    }

    @GetMapping("/list-of-products/{id}") //localhost:8080/list-of-products/2
    public String getProductByPage(@PathVariable("id") long id, Model model){
        if(id > 0) {
            for(Product temp : listOfProducts){
                if(temp.getId() == id){
                    model.addAttribute("packet", temp);
                    return "one-product-page";
                }
            }
        }
        model.addAttribute("packetError", "Wrong id");
        return "error-page";
    }

    @GetMapping("/add-product") //localhost:8080/add-product
    public String getAddProduct(Model model){
        model.addAttribute("product", new Product()); //send an empty product
        return "add-product-page";
    }
    @PostMapping("/add-product")
    public String postAddProduct(Product product){
        //TODO verify if this product already exists
        Product temp = new Product(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
        listOfProducts.add(temp);
        return "redirect:/list-of-products";
    }

    //TODO delete

    @GetMapping("/update-product/{id}") //localhost:8080/update-product/1
    public String getUpdateProduct(@PathVariable("id") long id, Model model){
        if(id > 0){
            for(Product temp : listOfProducts){
                if(temp.getId() == id){
                    model.addAttribute("product", temp);
                    return "update-product-page";
                }
            }
        }
        model.addAttribute("packetError", "Wrong id");
        return "error-page";
    }
    @PostMapping("/update-product/{id}")
    public String postUpdateProduct(@PathVariable("id") long id, Product product){
        for(Product temp : listOfProducts){
            if(temp.getId() == product.getId()){
                temp.setTitle(product.getTitle());
                temp.setDescription(product.getDescription());
                temp.setPrice(product.getPrice());
                temp.setQuantity(product.getQuantity());

                return "redirect:/list-of-products/" + id;
            }
        }
        return "redirect:/error";
    }
    @GetMapping("/error") //localhost:8080/error
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }

}
