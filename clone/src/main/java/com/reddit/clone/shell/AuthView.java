package com.reddit.clone.shell;

import com.reddit.clone.controller.RegistrationController;
import com.reddit.clone.controller.UserController;
import com.reddit.clone.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class AuthView {
    @Autowired
    RegistrationController registrationController;
    @Autowired
    UserController userController;

    @GetMapping("/register")
    public String registerView(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "registration";
    }
    @GetMapping("/login")
    public String loginView(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/auth-login")
    public String authLoginView(@ModelAttribute("user") User user) {

        ResponseEntity<?> re = userController.getUserByUsername(user.getUsername());
        if (re.getStatusCode() == HttpStatus.OK && Objects.equals(((User) re.getBody()).getPassword(), user.getPassword())) {
            return "home";
        }
        return "login";
    }
    @PostMapping("/auth-register")
    public String authRegisterView(@ModelAttribute("user") User user) {
        ResponseEntity<?> re = registrationController.saveUser(user);
        if (re.getStatusCode() == HttpStatus.CREATED) {
            return "home";
        }
        return "404";
    }
}
