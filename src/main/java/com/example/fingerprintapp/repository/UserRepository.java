package com.example.fingerprintapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fingerprintapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByFingerprint(byte[] fingerprint);
}
