package com.example.fingerprintapp.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.fingerprintapp.entity.User;
import com.example.fingerprintapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        System.out.println("Saving User: " + user.getName());
        userRepository.save(user);
    }

    @Override
    public User getUserByFingerprint(byte[] fingerprint) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println("Checking User: " + user.getName());
            System.out.println("Stored Fingerprint: " + Base64.getEncoder().encodeToString(user.getFingerprint()));

            if (Arrays.equals(user.getFingerprint(), fingerprint)) {
                System.out.println("Match Found!");
                return user;
            }
        }
        System.out.println("No Match Found.");
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
