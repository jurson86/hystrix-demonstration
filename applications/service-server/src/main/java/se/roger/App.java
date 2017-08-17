package se.roger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;
import java.util.Random;

@SpringBootApplication
@RestController
public class App {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/recommended", method = RequestMethod.GET)
    public String RetVal() throws InterruptedException {
        log.info("Processing returning dummy");
        Integer myInt = randomNum();
        log.info("sleeping for: " + myInt);
        TimeUnit.SECONDS.sleep(myInt);
        return "very important string here";
    }

    private Integer randomNum() {
        int[] nums = {1, 2, 3, 4, 5};
        Random generator = new Random();
        int randomIndex = generator.nextInt(nums.length);
        return nums[randomIndex];
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

}