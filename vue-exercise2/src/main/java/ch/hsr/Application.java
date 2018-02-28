package ch.hsr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@RestController
public class Application {

    final private static Map<String, String> portfolio = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/portfolio/{uuid}")
    public String get(@PathVariable("uuid") String uuid) {
        String retVal = portfolio.get(uuid);
        if(retVal == null) {
            return "[]";
        }
        return retVal;
    }
    @RequestMapping(value = "/portfolio/{uuid}", method = RequestMethod.POST)
    //@PostMapping("/portfolio/{uuid}")
    public void set(HttpEntity<String> httpEntity, @PathVariable("uuid") String uuid) {
        String json = httpEntity.getBody();
        portfolio.put(uuid, json);
    }
}