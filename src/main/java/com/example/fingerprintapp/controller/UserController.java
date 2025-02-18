package com.example.fingerprintapp.controller;

import java.util.Base64;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.fingerprintapp.entity.User;
import com.example.fingerprintapp.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
    
    @GetMapping("/register")
    public String getRegisterPageget() {
    	return "register";
    }

    @PostMapping("/register-user")
    public String registerUser(@RequestParam("name") String name, 
                               @RequestParam("fingerprint") String fingerprintBase64) {
        try {
            byte[] fingerprintData = Base64.getDecoder().decode(fingerprintBase64);
            System.out.println("Storing Fingerprint for user: " + name);
            System.out.println("Fingerprint Data (Base64): " + fingerprintBase64);

            User user = new User(name, fingerprintData);
            userService.addUser(user);
        } catch (IllegalArgumentException e) {
            return "redirect:/register?error=Invalid fingerprint data";
        }
        return "redirect:/";
    }

    @PostMapping("/find")
    public String findUserByFingerprint(@RequestParam("fingerprint") String fingerprintBase64, Model model) {
        try {
            byte[] fingerprintData = Base64.getDecoder().decode(fingerprintBase64);
            System.out.println("Searching for Fingerprint (Base64): " + fingerprintBase64);

            User user = userService.getUserByFingerprint(fingerprintData);
            if (user != null) {
                System.out.println("User Found: " + user.getName());
                model.addAttribute("user", user);
            } else {
                System.out.println("User Not Found");
                model.addAttribute("error", "User not found!");
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid fingerprint data!");
        }

        return "find-user";
    }
}
