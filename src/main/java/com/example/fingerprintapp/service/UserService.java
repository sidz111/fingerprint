package com.example.fingerprintapp.service;

import java.util.List;

import com.example.fingerprintapp.entity.User;

public interface UserService {
	
	User addUser(User user);
	
	User getUserById(Long id);
	
	List<User> getAllUsers();
	
	void deleteUserById(Long id);
	
	User getUserByFingerprint(byte[] fingerprint);
	
	

}
