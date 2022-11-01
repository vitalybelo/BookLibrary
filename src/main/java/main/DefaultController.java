package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String index() {

        String s = "Hello<br/>";
        s = s.concat(new Date().toInstant().toString() + "<br/>");
        s = s.concat("Asta La Vista");
        return s;

    }

}
