package com.example.fingerprintapp.service;

import com.example.fingerprintapp.entity.User;
import com.example.fingerprintapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User addUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return null;
		} else {
			return user.get();
		}
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByFingerprint(byte[] fingerprint) {
		Optional<User> user = Optional.ofNullable(userRepository.findByFingerprint(fingerprint));
		if (user.isEmpty()) {
			return null;
		} else {
			return user.get();
		}
	}

}
