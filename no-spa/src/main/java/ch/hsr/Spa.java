package ch.hsr;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Spa {
    @GetMapping("/spa/{value}")
    public String spa(@PathVariable("value") String value) {
        return "{\"value\": \""+value.toUpperCase()+"\"}";
    }
}

