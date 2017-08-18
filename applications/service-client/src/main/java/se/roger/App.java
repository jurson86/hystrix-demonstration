package se.roger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
@EnableHystrix
public class App {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    @RequestMapping(value = "/to-read", method = RequestMethod.GET)
    public String transfer() {
        log.info("Asking server for important message");
        return bookService.readingList();
    }

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(App.class, args);
        new SpringApplicationBuilder(App.class).web(true).run(args);
    }
}