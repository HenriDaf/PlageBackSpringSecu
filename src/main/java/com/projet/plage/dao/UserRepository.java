package com.projet.plage.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.plage.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

}
