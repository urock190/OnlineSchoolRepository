package webapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String getHomepage() {
        return "index";
    }

    @GetMapping("/quick-test")
    public String getQuickTestPage() {
        return "quick-test";
    }
}