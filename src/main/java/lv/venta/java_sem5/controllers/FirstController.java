package lv.venta.java_sem5.controllers;

import lv.venta.java_sem5.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

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
}
