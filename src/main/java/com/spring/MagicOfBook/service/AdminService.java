package com.spring.MagicOfBook.service;

import com.spring.MagicOfBook.dto.LoginDTO;
import com.spring.MagicOfBook.entity.Admin;
import com.spring.MagicOfBook.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public boolean validateUser(LoginDTO loginDTO) {
        Admin admin = adminRepository.findByUsername(loginDTO.getUsername());
        if (admin != null) {
            return loginDTO.getPassword().equals(admin.getPassword());
        }
        return false;
    }

    public Admin getAdmin(String username){
        Admin admin = adminRepository.findByUsername(username);
        return admin;
    }

    public Admin createAdmin(Admin admin) {
        Admin newAdmin = null;
        Admin existingAdmin = null;

        try {
            existingAdmin = adminRepository.findByUsername(admin.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (existingAdmin == null) {
            newAdmin = adminRepository.save(admin);
            return newAdmin;
        }else {
            return existingAdmin;
        }
    }
}
