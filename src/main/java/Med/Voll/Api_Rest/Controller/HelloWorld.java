package Med.Voll.Api_Rest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @GetMapping()
    public String helloWorld(){
        System.out.println("Hello World");
        return "Hello World";
    }

}
