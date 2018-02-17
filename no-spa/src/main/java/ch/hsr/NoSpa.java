package ch.hsr;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoSpa {
    @GetMapping("/nospa/{value}")
    public String nospa(Model model, @PathVariable("value") String value) {
        model.addAttribute("name", value.toUpperCase());
        return "nospa";
    }
}

