package lv.venta.java_sem5.controllers;

import lv.venta.java_sem5.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FirstController {

    private final ArrayList<Product> listOfProducts = new ArrayList<>(List.of(new Product ("Apple", "delicious", 1.2f, 5), new Product ("Orange", "orange", 0.52f, 60), new Product("Banana", "yellow", 1.29f, 20)));


    @GetMapping("/hello") //localhost:8080/hello
    public String getHelloFunc(){
        System.out.println("Hello World");
        return "hello-page"; //here is the page hello-page.html
    }

    @GetMapping("/msg") //localhost:8080/msg
    public String getMsgFunc(Model model){
        model.addAttribute("packet", "Message from good girl");
        System.out.println("good girl");
        return "msg-page"; //msg-page.html
    }

    @GetMapping("/one-product") //localhost:8080/one-product
    public String getOnProductFunc(Model model){
        Product product = new Product("Apple", "delicious", 1.2f, 5); //1.2 double => 1.2f float
        model.addAttribute("packet", product);
        return "one-product-page";
    }

    @GetMapping("/list-of-products") //localhost:8080/list-of-products
    public String getListOfProductsFunc(Model model){
        model.addAttribute("packet", listOfProducts); //I put list of products into the box
        return "list-of-products-page"; //I return page to show user the data
    }


}
