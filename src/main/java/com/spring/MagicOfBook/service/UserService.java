package com.spring.MagicOfBook.service;

import com.spring.MagicOfBook.dto.LoginDTO;
import com.spring.MagicOfBook.entity.User;
import com.spring.MagicOfBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user != null) {
            return loginDTO.getPassword().equals(user.getPassword());
        }
        return false;
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        User newUser = null;
        User existingUser = null;

        try {
            existingUser = userRepository.findByUsername(user.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (existingUser == null) {
            newUser = userRepository.save(user);
            return newUser;
        }else {
            return existingUser;
        }
    }
}
