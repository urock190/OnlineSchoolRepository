package webapi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import webapi.dto.UserDto;
import webapi.service.UserService;
import webapi.validation.UserAlreadyExistException;

@Controller
public class MainController {
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

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
    public String login() {
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto) {
        try {
            userService.registerNewUserAccount(userDto);
        } catch (final UserAlreadyExistException ex) {
            ModelAndView mav = new ModelAndView("signup", "user", userDto);
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
        return new ModelAndView("register_success", "user", userDto);
    }

    @GetMapping("/logout_success")
    public String logout() {
        return "logout_success";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}