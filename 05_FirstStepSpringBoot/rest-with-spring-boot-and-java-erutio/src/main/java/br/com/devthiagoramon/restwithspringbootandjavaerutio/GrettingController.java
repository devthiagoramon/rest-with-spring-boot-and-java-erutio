package br.com.devthiagoramon.restwithspringbootandjavaerutio;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin("*")
public class GrettingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/gretting")
    public Gretting gretting(@RequestParam(value = "name",
            defaultValue = "World") String name){
        return new Gretting(counter.incrementAndGet(), String.format(template, name));
    }

}
