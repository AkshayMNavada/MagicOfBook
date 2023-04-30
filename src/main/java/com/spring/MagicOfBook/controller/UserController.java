package com.spring.MagicOfBook.controller;

import com.spring.MagicOfBook.dto.LoginDTO;
import com.spring.MagicOfBook.entity.User;
import com.spring.MagicOfBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpSession httpSession) {
        if (userService.validateUser(loginDTO)) {
            User user = userService.getUserByUsername(loginDTO.getUsername());
            httpSession.setAttribute("email", user.getEmail());
            httpSession.setAttribute("username", loginDTO.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body("User " + loginDTO.getUsername() + " is logged in now");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession httpSession) {
        String username = httpSession.getAttribute("username").toString();
        if (username != null) {
            httpSession.removeAttribute("username");
            httpSession.removeAttribute("email");
            return ResponseEntity.status(HttpStatus.OK).body("User logged out successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not logged in!");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User newUser = userService.createUser(user);
        if (newUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("New user registered successfully!!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with username " + user.getUsername() + " already exists");
    }
}
