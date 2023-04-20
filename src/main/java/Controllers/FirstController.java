package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hello") //localhost:8080/hello
    public String getHelloFunc(){
        System.out.println("Hello World");
        return "hello-page"; //here is the page hello-page.html
    }
}
