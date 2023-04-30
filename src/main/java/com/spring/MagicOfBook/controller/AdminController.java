package com.spring.MagicOfBook.controller;

import com.spring.MagicOfBook.dto.LoginDTO;
import com.spring.MagicOfBook.entity.Admin;
import com.spring.MagicOfBook.entity.User;
import com.spring.MagicOfBook.service.AdminService;
import com.spring.MagicOfBook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            if (adminService.validateUser(loginDTO)) {
                Admin admin = adminService.getAdmin(loginDTO.getUsername());
                session.setAttribute("email", admin.getEmail());
                session.setAttribute("username", loginDTO.getUsername());
                return ResponseEntity.status(HttpStatus.OK).body("Admin " + loginDTO.getUsername() + " is logged in now");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin " + session.getAttribute("username") + " is already logged in");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        String name = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");
        if (name != null && email != null) {
            session.removeAttribute("email");
            session.removeAttribute("username");
            return ResponseEntity.status(HttpStatus.OK).body("Admin " + name + " logged out successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No admin is logged in");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Admin admin) {
        Admin admin1 = adminService.createAdmin(admin);
        if (admin1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("New admin registered successfully!!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin with username " + admin.getUsername() + " already exists");
    }
}
