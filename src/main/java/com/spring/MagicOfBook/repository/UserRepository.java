package com.spring.MagicOfBook.repository;

import com.spring.MagicOfBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
