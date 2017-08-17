package se.roger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
public class App {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/recommended", method = RequestMethod.GET)
    public String RetVal() {
        log.info("Processing returning dummy");
        return "very important string here";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

}