package com.example.fingerprintapp.service;

import java.util.List;

import com.example.fingerprintapp.entity.User;

public interface UserService {
    void addUser(User user);
    User getUserByFingerprint(byte[] fingerprint);
    List<User> getAllUsers();
}
