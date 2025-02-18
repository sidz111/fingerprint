package com.example.fingerprintapp.controller;

import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fingerprintapp.entity.User;
import com.example.fingerprintapp.service.UserServiceImpl;

@Controller
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register-user")
    public String registerUser(@RequestParam("name") String name, 
                               @RequestParam("fingerprint") String fingerprintBase64) {
        byte[] fingerprintData = Base64.getDecoder().decode(fingerprintBase64);
        User user = new User(name, fingerprintData);
        userServiceImpl.addUser(user);
        return "redirect:/";
    }

}
