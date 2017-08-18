package se.roger;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@Service
public class BookService {

    private final RestTemplate restTemplate;

    public BookService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10")
    })
    public String readingList() {
        URI uri = URI.create("http://localhost:8000/recommended");
        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "sry. I could not contact server so returning this instead";
    }
}
