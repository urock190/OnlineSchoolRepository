package webapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String saveCourse() {
        return "redirect:/";
    }
}